package reseau;

/**
 * Cette classe représente un arbre binaire de recherche pour un réseau social.
 */
public class ReseauBST extends BinarySearchTree {
    /**
     * Cette classe représente les noeuds de la classe de l'arbre binaire.
     */
    public class ReseauNode extends Node{

        ReseauNode[] friendList;
        String username;

        /**
         * Constructeur de ReseauNode
         * @param userNumber Le numéro d'utilisateur unique
         * @param user Le nom de l'utilisateur
         * @param listeAmi  La liste d'ami de l'utilisateur
         */
        public ReseauNode(int userNumber, String user, ReseauNode[] listeAmi) {
            super(userNumber);
            friendList = listeAmi;
            username = user;

        }

    }


    int indexTempList;
    String[] tempList;

    int numberUser;

    ReseauNode userInfo;


    /**
     * Cette classe retourne le nombre d'ami(s) de l'utilisateur en question.
     * @param user Le numéro de l'utilisateur
     * @return Le nombre d'ami(s) de "user"
     */
    public int compte_ami(int user){
        if (!search(user)){
            throw new IllegalStateException("L'utilsateur n'est pas dans le systeme");
        }
        ReseauNode currentNode = (ReseauNode) root;

        while (currentNode.key != user ){
            if (user < currentNode.key){
                currentNode = (ReseauNode) currentNode.left;
            }
            else{
                currentNode = (ReseauNode) currentNode.right;
            }
        }
        if (currentNode.friendList.length == 1 && currentNode.friendList[0] == null){
        return 0;
        }
        return currentNode.friendList.length;

    }

    /**
     * Cette classe rajoute un nouvel utilisateur dans l'arbre
     * @param userNumber Le numéro unique de l'utilisateur
     * @param username Le nom de l'utilisateur
     */
    public void ajoute_nouvel_utilisateur(int userNumber, String username) {
        ReseauNode[] friendlist = new ReseauNode[1];
        ReseauNode newReseauNode = new ReseauNode(userNumber, username, friendlist);

        if (isEmpty()){
            root = newReseauNode;
            incrmenteSize();
        }
        else{
            ReseauNode current = (ReseauNode) root;
            ReseauNode parent = null;

            while (true){
                parent = current;
                if (userNumber < current.key){
                    current = (ReseauNode) current.left;
                    if (current == null){
                        parent.left = newReseauNode;
                        incrmenteSize();
                        return;
                    }
                }
                else if (userNumber > current.key){
                    current = (ReseauNode) current.right;
                    if (current == null){
                        parent.right = newReseauNode;
                        incrmenteSize();
                        return;

                    }
                }
                else {throw new IllegalStateException("L'identifiant choisi est deja present dans le systeme.");}

            }
        }
    }

    /**
     * Cette classe ajoute un utilisateur dans la liste d'ami de tous les autres utilisateurs.
     * @param userName , l'utilisteur à rajouter dans la liste d'ami des autres utilisateurs.
     */
    public void ami_tout_le_monde(String userName){
        if (isEmpty()){
            throw new IllegalStateException("Il n'y a pas d'utilisateur dans le systeme");
        }

        if (!(isUserInTheSys((ReseauNode) root, userName))){
            throw new IllegalStateException("L'utilsateur n'est pas dans le systeme");
        }
        amiToutLeMonde(userName, (ReseauNode) root,userInfo);


    }

    /**
     * Cette classe privée se fait lancer par la classe ami_tout_le_monde et s'occupe de rajoyte l'utilisateur
     * en question dans la liste d'ami des autres utilisateurs de facon recursive.
     * @param username , nom de l'utilisateur à rajouter dans la liste d'ami des autres utilisateurs.
     * @param currentNode , le noeud qui se fait ajouter "username" dans sa liste d'ami.
     * @param userInfo , le noeud/les informations complètes de "username".
     */
    private void amiToutLeMonde(String username, ReseauNode currentNode, ReseauNode userInfo){
        if (currentNode.username != username){


            boolean alreadyFriend = false;          // Verifier s'ils etaient deja amis, l'ajouter s'ils etaient pas.

            loop:
            if (compte_ami(currentNode.key) != 0){
                for (ReseauNode i : currentNode.friendList){
                    if (i.username == username){
                        alreadyFriend = true;
                        break loop;
                    }
                }
            }

            if (!alreadyFriend){

                if (currentNode.friendList.length == 1 && currentNode.friendList[0]== null){
                    currentNode.friendList[0] = userInfo;
                }
                else{
                    ReseauNode[] temp = new ReseauNode[currentNode.friendList.length + 1];

                    for (int i = 0; i<currentNode.friendList.length; i++){
                        temp[i] = currentNode.friendList[i];
                    }

                    temp[currentNode.friendList.length] = userInfo; // A regler

                    currentNode.friendList = temp;
                }
            }

        }

        if (currentNode.left != null){
            amiToutLeMonde(username, (ReseauNode) currentNode.left,userInfo);
        }

        if (currentNode.right != null){
            amiToutLeMonde(username, (ReseauNode) currentNode.right,userInfo);
        }
    }


    /**
     * Cette classe retourne la totatlité de l'arbre de recherche en ordre croissant.
     * @return L'arbre binaire en ordre croissant de numéro d'utilisateur
     */
    public StringBuilder liste_noms_dans_ordre(){
        if (isEmpty()){
            throw new IllegalStateException("Il n'y a pas d'utilisateur dans le système");
        }
        indexTempList = 0;
        tempList = new String[size()];
        recursionNomOrdre((ReseauNode) root);       //Recursion qui remplie le tableau de facon infixe avec O(n)
        return printList(tempList);
    }

    /**
     * Cette classe parcours tout l'arbre de facon infixe récursivement et mets chaque noeuds visite dans un tableau
     * @param currentNode , le noeud visite
     */
    private void recursionNomOrdre(ReseauNode currentNode){

        if ((ReseauNode) currentNode.left != null){
            recursionNomOrdre((ReseauNode) currentNode.left);
        }
        tempList[indexTempList] = currentNode.username;
        indexTempList++;
        if ((ReseauNode) currentNode.right != null){
            recursionNomOrdre((ReseauNode) currentNode.right);
        }
    }


    /**
     * Visite tout l'arbre récursivement et retourne si l'utilisateur "username" est present dans l'arbre.
     * @param reseauNode , le noeud visite
     * @param username , le nom de l'utilisateur qu'on veut savoir s'il est présent dans l'arbre de recherche
     * @return vrai si "username" est présent, false sinon.
     */
    private boolean isUserInTheSys(ReseauNode reseauNode, String username){
        if (reseauNode.username == username){
            userInfo = reseauNode;
            return true;
        }

        if (reseauNode.left != null && reseauNode.right != null){
            return (isUserInTheSys((ReseauNode) reseauNode.left, username) || isUserInTheSys((ReseauNode) reseauNode.right, username));
        }
        else if (reseauNode.left != null && reseauNode.right == null) {
            return (isUserInTheSys((ReseauNode) reseauNode.left, username));
        }
        else if (reseauNode.left == null && reseauNode.right != null) {
            return (isUserInTheSys((ReseauNode) reseauNode.right, username));
        }
        return false;
    }

    /**
     * Cette classe prend le numéro d'un utilisateur et retourne une liste de recommendation d'amis. Les utilisateurs recommandés
     * sont les amis des amis de l'utilisateur avec l'identifiant "userNumber" lesquelles qui ne sont pas déjà ami avec "userNumber".
     * @param userNumber , l'utilisateur qui se fait recommender des amis.
     * @return une liste de recommendation d'ami pour "userNumber".
     */
    public StringBuilder recommander(int userNumber){
        if (isEmpty()){
            throw new IllegalStateException("Il n'y a pas d'utilisateur dans le systeme");
        }
        if (!search(userNumber)){
            throw new IllegalStateException("L'utilsateur n'est pas dans le systeme");
        }


        ReseauNode currentNode = (ReseauNode) root;

        while (currentNode.key != userNumber){              //Trouver l'utilisateur dans le systeme
            if (userNumber < currentNode.key){
                currentNode = (ReseauNode) currentNode.left;
            }
            else{
                currentNode = (ReseauNode) currentNode.right;
            }
        }

        String[] friendSuggestion = new String[1];
        boolean currentAlreadyFriendWithJ;

        if (!(currentNode.friendList[0]==null)){
            for (ReseauNode i : currentNode.friendList){    //amis de currentNode
                if (i.friendList[0] != null){
                    for (ReseauNode j : i.friendList){//amis des amis de currentNode
                        if (j != currentNode){        // Si j nest pas le currentNode
                            currentAlreadyFriendWithJ = false;

                            thirdLoop:
                            for (ReseauNode k : currentNode.friendList){      //Verifier si currentNode est deja ami avec l'ami de j
                                if (k == j){
                                    currentAlreadyFriendWithJ = true;
                                    break thirdLoop;
                                }
                            }



                            if (!currentAlreadyFriendWithJ){            //S'ils currentNode et j ne sont pas deja ami, j va dans suggestion
                                if (friendSuggestion.length == 1 && friendSuggestion[0]==null){
                                    friendSuggestion[0] = j.username;
                                }
                                else{
                                   boolean alreadyInSuggestion = false;
                                   multipleSuggestion:
                                   for (int m=0;m< friendSuggestion.length;m++){
                                       if (friendSuggestion[m]==j.username){
                                           alreadyInSuggestion = true;
                                           break multipleSuggestion;
                                       }
                                   }
                                   if (!alreadyInSuggestion){
                                        String[] temp = new String[friendSuggestion.length+1];

                                        for (int l = 0; l < friendSuggestion.length;l++){
                                            temp[l]=friendSuggestion[l];
                                        }
                                        temp[friendSuggestion.length] = j.username;
                                        friendSuggestion = temp;
                                   }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (friendSuggestion.length == 1 && friendSuggestion[0]==null){
            StringBuilder builder = new StringBuilder("Il n'y a pas de suggestions d'ami pour "+ currentNode.username);
            return builder;
        }
        else {
            return printList(friendSuggestion);
        }

    }

    /**
     * Cette classe retourne un tableau en String.
     * @param list , le tableau a retourné en String.
     * @return le table "list" en String.
     */
    private StringBuilder printList(String[] list) {
        StringBuilder builder = new StringBuilder("[");
        for (int i =0; i < list.length;i++) {
            builder.append(list[i]);
            if (i != list.length-1){
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder;

    }


}
