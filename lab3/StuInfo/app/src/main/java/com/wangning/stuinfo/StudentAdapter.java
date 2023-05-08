package com.wangning.stuinfo;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    /**
     * 学生信息列表
     */
    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    /**
     * 创建 ViewHolder 实例：加载 student_item 布局，创建一个 ViewHolder 实例，
     * 并把加载出来的布局传入到构造函数当中，最后将 ViewHolder 实例返回
     *
     * @param parent   parent
     * @param viewType viewType
     * @return viewHolder(view)
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.student_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * 对 RecyclerView 子项的数据进行赋值，在每个子项被滚动到屏幕内时执行
     *
     * @param holder   holder
     * @param position position
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);

        Bitmap bitmap = ImageUtils.base642Bitmap(student.getAvatar());

        holder.avatar.setImageBitmap(bitmap);
//        holder.avatar.setImageResource(R.drawable.default_avatar);
        holder.name.setText(student.getName());
        holder.score.setText(Float.toString(student.getScore()));
    }

    /**
     * 一共的子项条数
     *
     * @return studentList.size()
     */
    @Override
    public int getItemCount() {
        return studentList.size();
    }

    /**
     * ViewHolder 复用条目对象
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;
        TextView score;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.info_avatar);
            name = itemView.findViewById(R.id.info_name);
            score = itemView.findViewById(R.id.info_score);
        }
    }
}
