package com.example.togengo.Dictionary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.togengo.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Dictionary_CustomAdapter extends ArrayAdapter implements View.OnClickListener, AdapterView.OnItemClickListener, Filterable {
    public static class ViewHolder {
        TextView word;
        ImageView imageStar;
        ImageView imageClear;
        ImageView imageDir;
    }
    int resourceId;
    ListBtnClickListener listBtnClickListener;
    Context context;
    ViewHolder holder = null;
    int getPosition;
    List<Directory> dirList;


    public Dictionary_CustomAdapter(Context context, int resource, ArrayList<ListViewBtnItem> list,
                         ListBtnClickListener clickListener, List<Directory> dir){
        super(context, resource, list);
        this.context = context;
        resourceId =resource;
        dirList = dir;
        this.listBtnClickListener = clickListener;
    }


    @Override
    public View getView(int position, View converView, ViewGroup parent){

        final int pos = position;
        getPosition = position;
        final Context context = parent.getContext();
        // 생성자로 부터 저장된 resourceId(Listview_btn_item) 에
        // 해당하는 Layout을 inflate하여 convertView참조 획득
        if(converView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converView = inflater.inflate(this.resourceId/*R.Layout.listview_btn_item*/, parent, false);
            holder = new ViewHolder();
            holder.word = (TextView) converView.findViewById(R.id.wordName);
            holder.imageClear = (ImageView) converView.findViewById(R.id.imageClear);
            holder.imageStar = (ImageView) converView.findViewById(R.id.imageStar);
            holder.imageDir = (ImageView) converView.findViewById(R.id.imageDirectory);
            converView.setTag(holder);
        } else
            holder = (ViewHolder) converView.getTag();

        // 화면에 표시될 View(Layout이 inflate된)로 부터 위젯에 대한 참조 획득
        if(dirList != null ) {
            holder.word.setText(position + 1 + ". " + dirList.get(position).getWord());
            if (dirList.get(position).getStar().equals("yes")) {
                holder.imageStar.setImageResource(R.drawable.starlight);
            } else {
                holder.imageStar.setImageResource(R.drawable.star);
            }
            if (dirList.get(position).getClear().equals("학습중")) {
                holder.imageClear.setImageResource(R.drawable.studying);
            } else {
                holder.imageClear.setImageResource(R.drawable.study);
            }
        }
        final ViewHolder finalHolder = holder;
        holder.imageDir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(resourceId);
                String word = indexMaker(finalHolder.word.getText().toString());
                Uri uri = uriMake(word);
                //인터넷창에 띄워줍니다.
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);


            }
        });


        return converView;
    }
    public interface ListBtnClickListener{ void onListBtnClick(int position); }



    @Override
    public void onClick(View v) {

    }
    public String indexMaker(String vo) {
        String[] cut = vo.split(". ");
        String idx = String.valueOf(cut[1]);

        return idx;
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }

    private Uri uriMake(String word) {
        String url = "https://en.dict.naver.com/#/search?query=";
        //클릭한 ListView의 값을 가져와줍니다
        return Uri.parse(url + word); //링크와 합성해서

    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {    }

}
class ListViewBtnItem{
    public String textStr;
    public void setText(String text){ textStr = text; }
    public String getText() { return  this.textStr; }

    @Override
    public String toString() {
        return  textStr;
    }
}
