package com.example.commueoflove.emergency;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.commueoflove.DailyPublicBenfit.UploadDonationActivity;
import com.example.commueoflove.Dao.ListItemOne;
import com.example.commueoflove.R;
import com.example.commueoflove.ToolClass.GlideRoundTransform;
import com.example.commueoflove.ToolClass.UrgentListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class UrgentFragment extends Fragment {

    XBanner banner;
    private List<ListItemOne> urgentList=new ArrayList<>();
    UrgentListAdapter urgentListAdapter;
    ListView listView;
    FloatingActionButton add;

    public UrgentFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static UrgentFragment newInstance(String param1, String param2) {
        UrgentFragment fragment = new UrgentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //获取控件
        View view = inflater.inflate(R.layout.fragment_urgent, container, false);
        banner = (XBanner)view.findViewById(R.id.xbanner);
        listView = (ListView)view.findViewById(R.id.urgent_list_view);


        //设置轮播
        List<String> img_list = new ArrayList<>();
//        img_list.add(R.drawable.img_resource);
//        img_list.add(R.drawable.img_donation);
//        img_list.add(R.drawable.img_urgent);
        //加载轮播
        img_list.add("https://pic.vjshi.com/2020-02-26/dfa13b12be5f8fe9713422a89ae22864/00001.jpg?x-oss-process=style/watermark");
        img_list.add("https://cn.bing.com/images/search?view=detailV2&ccid=ln615lsc&id=E33694DBC69EE44519FE0FC3825CB7107CC8C66F&thid=OIP.ln615lsc4sCLWe-xsXdTlwHaFj&mediaurl=https%3a%2f%2fimgcdn.thecover.cn%2f%40%2fimages%2f20200818%2f1597716389662051034.jpg%3fimageMogr2%2fthumbnail%2f1080x%3e%2fstrip%2fquality%2f95%2fignore-error%2f1%7cimageslim&exph=810&expw=1080&q=%e6%b4%aa%e7%81%be%e6%b1%82%e5%8a%a9%e5%9b%be%e7%89%87&simid=608000415463970236&FORM=IRPRST&ck=8ED2345FB5D0B305BB765C4DC832D81B&selectedIndex=2&ajaxhist=0&ajaxserp=0");
        img_list.add("https://cn.bing.com/images/search?view=detailV2&ccid=NcsC1u6R&id=5C3BDABB656CC7CC79085E2C3A5CCBFD7E518963&thid=OIP.NcsC1u6RSLknGaXH25ePYgHaEN&mediaurl=https%3a%2f%2ftse1-mm.cn.bing.net%2fth%2fid%2fR-C.35cb02d6ee9148b92719a5c7db978f62%3frik%3dY4lRfv3LXDosXg%26riu%3dhttp%253a%252f%252fn.sinaimg.cn%252ftranslate%252f20170809%252flQkB-fyitpmh5728055.jpg%26ehk%3dJHwmlN2IDJ7l87XzfnjuQ685gRl0w9H8oq8pFGGeSgw%253d%26risl%3d%26pid%3dImgRaw%26r%3d0%26sres%3d1%26sresct%3d1%26srh%3d740%26srw%3d1300&exph=330&expw=580&q=%e5%9c%b0%e9%9c%87%e7%81%be%e5%90%8e%e6%8f%b4%e5%8a%a9%e5%9b%be%e7%89%87&simid=608015533746882921&FORM=IRPRST&ck=8DAE31BD9E8548869FDD1F645004ED3C&selectedIndex=13&qpvt=%e5%9c%b0%e9%9c%87%e7%81%be%e5%90%8e%e6%8f%b4%e5%8a%a9%e5%9b%be%e7%89%87&ajaxhist=0&ajaxserp=0");
        banner.setData(img_list,null);
        // 设置XBanner的页面切换特效
        banner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        banner.setPageChangeDuration(1000);
        banner.setData(img_list,null);
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                RequestOptions myOptions = new RequestOptions()
                        .transform(new GlideRoundTransform(getContext(),10));
                Glide
                        .with(getContext())
                        .load(img_list.get(position))
                        .apply(myOptions)
                        .into((ImageView) view);
            }
        });

        //表单
        ListItemOne listItemOne = new ListItemOne(R.drawable.img_donation,"疫情隔离急需生活物资","一级","物资","未解决","成都","张女士","由于已经隔离了有3个月，所以物资缺乏，目前需要......");
        urgentList.add(0, listItemOne);
        urgentList.add(1, listItemOne);
        urgentList.add(2, listItemOne);
        urgentList.add(3, listItemOne);
        urgentList.add(4, listItemOne);
        //绑定listview和表单
        urgentListAdapter = new UrgentListAdapter(getContext(), R.layout.list_view_urgent, urgentList);
        listView.setAdapter(urgentListAdapter);

        setListViewHeightBasedOnChildren(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到详情页
//                Intent intent = new Intent(getActivity(), GongziDetailActivity.class);
//                startActivity(intent);
            }
        });

        //打开上传记录Activity
        add = (FloatingActionButton)view.findViewById(R.id.add_urgent);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UploadUrgentActivity.class);
                //传递用户账号信息
                startActivity(intent);
            }
        });

        return view;
    }

    public void setListViewHeightBasedOnChildren(ListView listView){
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }
}