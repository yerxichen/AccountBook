package com.example.softwise.mytestutil;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.softwise.tree.TreeHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import static com.softwise.tree.TreeHolder.*;

public class TreeActivity extends AppCompatActivity {
    private ListView lv_tree;
    private Context mContext;
    private AndroidTreeView tView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree);

        lv_tree= (ListView) findViewById(R.id.lv_tree);
        IconTreeItem nodesItem=new IconTreeItem();
        TreeNode root = TreeNode.root();
        TreeNode parent=new TreeNode("parent");
        TreeNode child1 = new TreeNode(new IconTreeItem().text);
        TreeNode child2=new TreeNode("child2");
        parent.addChildren(child1,child2);
        root.addChild(parent);

        tView=new AndroidTreeView(this,root);
        lv_tree.addView(tView.getView());


    }
}
