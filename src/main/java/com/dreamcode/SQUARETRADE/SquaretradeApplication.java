package com.dreamcode.SQUARETRADE;

import com.dreamcode.SQUARETRADE.models.Category;
import com.dreamcode.SQUARETRADE.services.GeneralTree;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SquaretradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SquaretradeApplication.class, args);

		Category root = new Category("Lawn & Garden,Furniture", "Floral");
		Category category1 = new Category("Minor Appliances", "Home Appliances");
		Category category2 = new Category("Home Appliances", "Furniture");
		Category category3 = new Category("Major Appliances", "Electronics");

		GeneralTree<Category> tree = new CategoryTree(root);
		GeneralTree<Category> subTree1 = new CategoryTree(category1);
		GeneralTree<Category> subTree2 = new CategoryTree(category2);
		GeneralTree<Category> subTree3 = new CategoryTree(category3);

		subTree1.addCategory(subTree2);
		subTree1.addCategory(subTree3);
		tree.addCategory(subTree1);

		Category category = tree.search("Electronics");
		int heightOfNode = tree.heightOfNode("Electronics");
		System.out.println(category.getName());
		System.out.println(category.getKeywords());
		System.out.println(heightOfNode);
		System.out.println(category.getParentCategory().getName());
	}

}
