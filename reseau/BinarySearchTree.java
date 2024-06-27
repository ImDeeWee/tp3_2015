package reseau;
/**
 * Classe représentant un arbre binaire de recherche.
 */

 public class BinarySearchTree {
    protected Node root;
    int size;

	/**
	 * Classe représentant un noeud dans l'arbre binaire de recherche.
	 */
    public class Node {
        public int key;

        public Node left;
        public Node right;
        
        /**
         * Constructeur de Node.
         *
         * @param item La clé du nouveau noeud.
         */
        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    /**
     * Constructeur du BST.
     */
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Vérifie si l'arbre est vide.
     *
     * @return true si l'arbre est vide, false sinon.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retourne le nombre de noeuds dans l'arbre.
     *
     * @return Le nombre de noeuds dans l'arbre.
     */
    public int size() {
        return size;
    }

    /**
     * Insère une nouvelle clé dans l'arbre binaire de recherche.
     *
     * @param key La clé à insérer.
     */
    public void insert(int key) {
        Node newNode = new Node(key); 
        if (isEmpty()) {
            root = newNode; 
            size++;
            return;
        }
        
        Node current = root;
        Node parent = null;
        
        while (true) {
            parent = current;
            if (key < current.key) { 
                current = current.left;
                if (current == null) { 
                    parent.left = newNode;
                    size++;
                    return;
                }
            } else if (key > current.key) { 
                current = current.right;
                if (current == null) { 
                    parent.right = newNode;
                    size++;
                    return;
                }
            } else {
          
                return;
            }
        }
    }


    /**
     * Cherche une clé dans l'arbre.
     *
     * @param key La clé à chercher.
     * @return true si la clé est trouvée, false sinon.
     */
    public boolean search(int key) {
    	Node t = root;
        return searchRec(t, key);
    }

    private boolean searchRec(Node t, int key) {
        
    	if (key == t.key) {
    		return true;
    	}
    	
    	else if (key < t.key && t.left != null) {
            return searchRec(t.left, key);
    	}
    	
    	else if (key > t.key && t.right != null) {
            return searchRec(t.right, key);
    	}
    	return false;
    }
    

    /**
     * Retourne la plus grande clé de l'arbre.
     *
     * @return La clé la plus grande.
     * @throws IllegalStateException Si l'arbre est vide.
     */
    public int getMax() {
        if (isEmpty()){
        	throw new IllegalStateException("Arbre est vide");
        }
        
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }
    
    /**
     * Supprime une clé de l'arbre.
     *
     * @param key La clé à supprimer.
     * @throws IllegalStateException Si la clé n'est pas dans l'arbre ou si l'arbre est vide.
     */
    public void remove(int key) {
    	if(!search(key)) {
    		throw new IllegalStateException("Key pas dans l'arbre.");
    	}
    	else if(isEmpty()) {
    		throw new IllegalStateException("Arbre est vide");
    	}
    	size--;
    	Node t = root;
        root = removeRec(t, key);
    }

    private Node removeRec(Node t, int key) {
    	
        if (key < t.key) {
            t.left = removeRec(t.left, key);}
        else if (key > t.key) {
            t.right = removeRec(t.right, key);}
        else {
            if (t.left == null)
                return t.right;
            else if (t.right == null)
                return t.left;

            t.key = minValue(t.right);
            t.right = removeRec(t.right, t.key);
        }

        return t;
    }

    int minValue(Node t) {
        int minv = t.key;
        while (t.left != null) {
            minv = t.left.key;
            t = t.left;
        }
        return minv;
    }
  
    /**
     * Mise à jour de chaque noeud pour contenir la somme des clés supérieurs.
     *
     * @param root Noeud racine.
     */
    public void updateBST(Node root) {
        updateBSTHelper(root, 0);
    }

    private void updateBSTHelper(Node t, int keyPre) {
        if (t == null) {
            return;
        }

        t.key += keyPre;

        updateBSTHelper(t.left, t.key); 
        updateBSTHelper(t.right, t.key);
    }

    
    public void incrmenteSize(){
        size++;
    }
    
    /**
     * Vérifie si deux tableaux donnent forment le même BST.
     *
     * @param array1 Premier tableau.
     * @param array2 Deuxième tableau.
     * @return true si ils forment le même BST, false sinon.
     */
    
    public boolean areSameBST(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        if (array1.length == 0) {
            return true;
        }
        
        if (array1[0] != array2[0]) {
            return false;
        }

        int[] left1 = new int[array1.length];
        int[] right1 = new int[array1.length];
        int[] left2 = new int[array2.length];
        int[] right2 = new int[array2.length];
        
        int leftCount1 = 0;
        int rightCount1 = 0; 
        int leftCount2 = 0;
        int rightCount2 = 0;
        
        
        for (int i = 1; i < array1.length; i++) {
            if (array1[i] < array1[0]) {
                left1[leftCount1++] = array1[i];
            } else {
                right1[rightCount1++] = array1[i];
            }
        }
        
        for (int i = 1; i < array2.length; i++) {
            if (array2[i] < array2[0]) {
                left2[leftCount2++] = array2[i];
            } else {
                right2[rightCount2++] = array2[i];
            }
        }
        

        return areSameBST(trimArray(left1, leftCount1), trimArray(left2, leftCount2)) && 
        	   areSameBST(trimArray(right1, rightCount1), trimArray(right2, rightCount2));
    }
    
    private static int[] trimArray(int[] array, int newLength) {
        int[] result = new int[newLength];
        for (int i = 0; i < newLength; i++) {
            result[i] = array[i];
        }
        return result;
    }

}