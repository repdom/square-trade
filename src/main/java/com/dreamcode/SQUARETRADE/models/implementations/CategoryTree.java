package com.dreamcode.SQUARETRADE.models.implementations;

import com.dreamcode.SQUARETRADE.DAOs.GeneralTree;
import com.dreamcode.SQUARETRADE.models.Category;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class CategoryTree implements GeneralTree<Category> {
    private Category root;
    private LinkedList<GeneralTree<Category>> children;

    public CategoryTree(Category root) {
        this.root = root;
        this.children = new LinkedList<>();
    }

    @Override
    public Category root() {
        return this.root;
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public int height() {
        if(isLeaf()) {
            return 0;
        }
        int max = 0;
        for(int i = 0; i < children.size(); i++) {
            int h = children.get(i).height();
            if(max < h) {
                max = h;
            }
        }
        max+=1;
        return max;
    }

    @Override
    public int heightOfNode(String category) {
        if(isLeaf()) {
            return 0;
        }
        int max = 0;
        for(int i = 0; i < children.size(); i++) {
            int h = children.get(i).height();
            if(((CategoryTree)children.get(i)).root.getKeywords().toUpperCase(Locale.ROOT).contains(category.toUpperCase(Locale.ROOT))) {
                if(max < h) {
                    max = h;
                }
                return max;
            }
            max += ((CategoryTree) children.get(i)).heightOfNode(category) + 1;
        }
        return max;
    }

    @Override
    public void addCategory(GeneralTree<Category> subTree) {
        children.add(subTree);
    }

    @Override
    public Category search(String category) {
        return _search(new LinkedList<>(), category, new Category());
    }

    Category _search(List<Category> result, String category, Category parent) {
        Category categoryObject = new Category();
        for(int i = 0; i < getNumberOfSubtrees(); i++) {
            if(((CategoryTree)children.get(i)).root.getKeywords().toUpperCase(Locale.ROOT).contains(category.toUpperCase(Locale.ROOT))) {
                categoryObject = ((CategoryTree)children.get(i)).root;
                categoryObject.setParentCategory(parent);
                return categoryObject;
            }
            categoryObject = ((CategoryTree) children.get(i))._search(new LinkedList<>(), category, ((CategoryTree)children.get(i)).root);
        }
        return categoryObject;
    }

    @Override
    public int getNumberOfSubtrees() {
        return children.size();
    }
}
