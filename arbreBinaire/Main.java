package arbreBinaire;

public class Main {
	public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Taille de l'arbre : " + bst.size());
        System.out.println("Maximum: " + bst.getMax());
        System.out.println("Recherche de 40 : " + bst.search(40));
        System.out.println("Recherche de 90 : " + bst.search(90));
        
        bst.remove(80);
        System.out.println("Après la suppression de 80, nouvelle taille : " + bst.size());
        System.out.println("Nouveau maximum après suppression : " + bst.getMax());
        
        bst.updateBST(bst.root);
        System.out.println("Nouveau maximum après update : " + bst.getMax());
        
        int[] array1 = {5, 6, 3, 1};
        int[] array2 = {5, 3, 1, 6 };
        System.out.println(bst.areSameBST(array1,array2)); //devrait retourner true

        array1[0]=50;
        System.out.println(bst.areSameBST(array1,array2)); //devrait retourner false
	}
}
