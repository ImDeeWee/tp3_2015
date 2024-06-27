package reseau;

public class Main {
    public static void main(String[] args) {

        ReseauBST bst = new ReseauBST();

        System.out.println("--Yoda a rejoint le reseau social--\n");
        bst.ajoute_nouvel_utilisateur(2, "Yoda");

        System.out.println("--Naruto Uzumaki a rejoint le reseau social--\n");
        bst.ajoute_nouvel_utilisateur(7, "Naruto Uzumaki");

        System.out.println("--Eren Jaeger a rejoint le reseau social--\n");
        bst.ajoute_nouvel_utilisateur(10, "Eren Jaeger");

        System.out.println("Eren devrait avoir aucun ami: "+bst.compte_ami(10)+" ami(s)\n");

        System.out.println("***Ajout d'Eren Jaeger dans la liste d'ami de tous les autres utilisateurs***\n");
        bst.ami_tout_le_monde("Eren Jaeger");

        System.out.println("Yoda devrait avoir un seul ami qui est Eren: "+bst.compte_ami(2)+" ami(s)\n");

        System.out.println("Eren devrait toujours avoir aucun ami: "+bst.compte_ami(10)+" ami(s)\n");

        System.out.println("***Ajout de Naruto dans la liste d'ami de tous les autres utilisateurs***\n");
        bst.ami_tout_le_monde("Naruto Uzumaki");

        System.out.println("Yoda devrait avoir deux amis qui sont Eren et Naruto: "+bst.compte_ami(2)+" ami(s)\n");

        System.out.println("--Russel Westbrook a rejoint le reseau social--\n");
        bst.ajoute_nouvel_utilisateur(0, "Russel Westbrook");

        System.out.println("La liste en ordre croissant du numero d'utilisateur devrait etre ainsi:");
        System.out.println("1. Russel Westbrook(0)");
        System.out.println("2. Yoda(2)");
        System.out.println("3. Naruto Uzumak(7)");
        System.out.println("4. Eren Jaeger(10)");
        System.out.println("Resultat de la fonction \033[3mliste_noms_dans_ordre\033[0m : "+bst.liste_noms_dans_ordre()+"\n");

        System.out.println("Puisque Russel n'a pas d'ami ("+ bst.compte_ami(0)+"), il ne devrait pas avoir de suggestion d'ami(s): "+(bst.recommander(0))+"\n");

        bst.ami_tout_le_monde("Yoda");
        System.out.println("Ajout de Yoda dans la liste d'ami de tous les autres utilisateurs\n");

        System.out.println("Puisque Russel a maintenant "+ bst.compte_ami(0)+" ami, qui est Yoda, Russel devrait avoir "+bst.compte_ami(2)+ " suggestion(s) d'ami: "+(bst.recommander(0))+"\n");

        System.out.println("***Ajout d'Eren Jaeger dans la liste d'ami de tous les autres utilisateurs***\n");
        bst.ami_tout_le_monde("Eren Jaeger");

        System.out.println("Maintenant que Russel est ami avec Eren, il devrait avoir seulement une suggestion d'ami: "+(bst.recommander(0))+"\n");

        System.out.println("--Lebron James a rejoint le reseau social--\n");
        bst.ajoute_nouvel_utilisateur(6,"Lebron James");

        System.out.println("La liste en ordre croissant du numero d'utilisateur devrait etre ainsi:");
        System.out.println("1. Russel Westbrook(0)");
        System.out.println("2. Yoda(2)");
        System.out.println("3. Lebron James(6)");
        System.out.println("4. Naruto Uzumak(7)");
        System.out.println("5. Eren Jaeger(10)");
        System.out.println("Resultat de la fonction \033[3mliste_noms_dans_ordre\033[0m : "+bst.liste_noms_dans_ordre());







    }



}
