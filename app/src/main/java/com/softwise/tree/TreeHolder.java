package com.softwise.tree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.softwise.mytestutil.R;
import com.unnamed.b.atv.model.TreeNode;

/**
 * Created by softwise on 2016/12/20.
 */

public class TreeHolder extends TreeNode.BaseNodeViewHolder<TreeHolder.IconTreeItem>{


    public TreeHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater=LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.tree_nodes, null, false);
        TextView child1= (TextView) view.findViewById(R.id.tv_child1);
        child1.setText(value.text);
        return view;
    }

    public static class IconTreeItem{
        public int icon;
        public String text;
    }
}
