package com.wangning.stuinfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.litepal.LitePal;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int CHOOSE_PHOTO = 1;
    private static final int GET_STORAGE_PERMISSION = 2;

    private static final String TAG = "MainActivity";

    private ImageView ivAvatar;
    private Button btnAvatar, btnSubmit, btnJump;
    private EditText etName, etScore;

    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化界面控件，并绑定事件监听器
     */
    private void initView() {
        ivAvatar = findViewById(R.id.picture);
        btnAvatar = findViewById(R.id.btn_select_avatar);
        btnSubmit = findViewById(R.id.btn_submit_info);
        btnJump = findViewById(R.id.btn_query_info);
        etName = findViewById(R.id.et_name);
        etScore = findViewById(R.id.et_score);

        btnAvatar.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnJump.setOnClickListener(this);
    }

    /**
     * 点击事件处理
     *
     * @param view view
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        String name;
        Float score;
        String avatar;
        Student student;
        switch (view.getId()) {
            case R.id.btn_select_avatar: // 从相册中选择头像，将头像展示在 R.id.picture 中
                // 打开相册：运行时权限处理 WRITE_EXTERNAL_STORAGE
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.
                        PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, GET_STORAGE_PERMISSION);
                } else {
                    openAlbum();
                }
                break;
            case R.id.btn_submit_info: // 将信息存储到数据库中
                name = etName.getText().toString().trim();
                score = Float.valueOf(etScore.getText().toString());
                avatar = ImageUtils.bitmap2Base64(this, bitmap);

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                }
                student = new Student(name, score, avatar);
                student.save();
                clearInfo();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_query_info: // 跳转至展示信息的 SubActivity
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                startActivity(intent);
        }
    }

    /**
     * 清空信息
     */
    private void clearInfo() {
        etName.setText(null);
        etScore.setText(null);
        ivAvatar.setImageResource(R.drawable.default_avatar);
    }

    /**
     * 打开相册
     */
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.
                        PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) { // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else { // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 4.4版本及以上解析封装过的 Uri，获得图片真实路径，根据图片路径显示图片
     *
     * @param data intent
     */
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) { // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) { // authority : media
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.
                    getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) { // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) { // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        // 根据图片路径显示图片
        displayImage(imagePath);
    }

    /**
     * 4.4版本以下的 Uri 未封装，获取图片路径显示图片
     *
     * @param data intent
     */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }


    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            bitmap = BitmapFactory.decodeFile(imagePath);
            ivAvatar.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
}