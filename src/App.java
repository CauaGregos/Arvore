public class App {
    public static void main(String[] args) throws Exception {
        Arvore<Integer> a = new Arvore<Integer>();
        Arvore<Integer> b = new Arvore<Integer>();

        try
        {
            a.inclua(1);
            a.inclua(2);
            a.inclua(3);
            a.inclua(4);
            a.remover(2);
            a.remover(1);
            System.out.println(a);


        }
        catch (Exception erro){}


        /*               Print da arvore            /     System.out.println(a);



        /           TESTE DO Metodo getQtdNos()            /
//        System.out.println(a.getQtdNos()); // 4



        /            TESTE DO Metodo TEM()            /
//        System.out.println(a.tem("Java")); // true
//        System.out.println(b.tem(2)); // false (null)


        /             TESTE DO EQUALS              /
//        System.out.println(a.equals(b));


        /             TESTE DO CLONE              /
//        Arvore<String> c = (Arvore<String>) a.clone();
//        System.out.println(c);


        /            TESTE EXCLUSÃO FOLHA SEM FILHO        */

        // try {
        //     a.exclusao("Java");
        // } catch (Exception e) {
        //     throw new RuntimeException(e);
        // }
        // System.out.println(b);
    
    }
}
