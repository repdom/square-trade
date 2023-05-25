package com.dreamcode.SQUARETRADE;

import com.dreamcode.SQUARETRADE.models.Category;
import com.dreamcode.SQUARETRADE.models.implementations.CategoryTree;
import com.dreamcode.SQUARETRADE.DAOs.GeneralTree;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SquaretradeApplicationTests {

	@Test
	void contextLoads() {
	}

	private GeneralTree<Category> createTree() {
		Category category1 = new Category("Minor Appliances", "Home Appliances");
		Category category2 = new Category("Home Appliances", "Furniture");
		Category category3 = new Category("Major Appliances", "Electronics");


		GeneralTree<Category> subTree1 = new CategoryTree(category1);
		GeneralTree<Category> subTree2 = new CategoryTree(category2);
		GeneralTree<Category> subTree3 = new CategoryTree(category3);

		subTree1.addCategory(subTree2);
		subTree1.addCategory(subTree3);
		return subTree1;
	}
	@Test
	@Order(1)
	public void getKeywordsOfCategory() {
		Category root = new Category("Lawn & Garden,Furniture", "Floral");
		GeneralTree<Category> tree = new CategoryTree(root);
		tree.addCategory(createTree());

		Category category = tree.search("Electronics");
		assertEquals(category.getName(), "Major Appliances");
	}

	@Test
	@Order(2)
	public void calcNodeLevelOfCategory() {

		Category root = new Category("Lawn & Garden,Furniture", "Floral");
		GeneralTree<Category> tree = new CategoryTree(root);
		tree.addCategory(createTree());

		int heightOfNode = tree.heightOfNode("Electronics");
		assertEquals(2, heightOfNode);
	}

	@Test
	@Order(3)
	public void calcNodeLevelOfCategoryNotMatch() {

		Category root = new Category("Lawn & Garden,Furniture", "Floral");
		GeneralTree<Category> tree = new CategoryTree(root);
		tree.addCategory(createTree());

		int heightOfNode = tree.heightOfNode("Floral");
		assertNotEquals(2, heightOfNode);
	}
	@Test
	@Order(4)
	public void calcNodeLevelOfCategoryNullPointerException() {

		Category root = new Category("Lawn & Garden,Furniture", "Floral");
		GeneralTree<Category> tree = new CategoryTree(root);
		tree.addCategory(createTree());

		Category category = tree.search("Electronics");
		assertEquals(category.getParentCategory().getName(), "Minor Appliances");
	}
	@Test
	@Order(5)
	public void parentCategory() {

		Category root = new Category("Lawn & Garden,Furniture", "Floral");
		GeneralTree<Category> tree = new CategoryTree(root);
		assertThrows(NullPointerException.class, ()->{
			tree.addCategory(null);
			int heightOfNode = tree.heightOfNode("Floral");
		});
	}
}
