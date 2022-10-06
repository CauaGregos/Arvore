import java.util.Objects;

public class Arvore<X extends Comparable<X>> implements Cloneable  // angle brackets
{


    private class No implements Cloneable {
        private X info;
        private No esq, dir;

        public No(X i) {
            this.esq = null;
            this.info = i;
            this.dir = null;
        }

        public No(No e, X i, No d) {
            this.esq = e;
            this.info = i;
            this.dir = d;
        }

        public No getEsq() {
            return this.esq;
        }

        public X getInfo() {
            return this.info;
        }

        public No getDir() {
            return this.dir;
        }

        public void setEsq(No e) {
            this.esq = e;
        }

        public void setInfo(X i) {
            this.info = i;
        }

        public void setDir(No d) {
            this.dir = d;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            No no = (No) o;
            return Objects.equals(info, no.info) && Objects.equals(esq, no.esq) && Objects.equals(dir, no.dir);
        }

        @Override
        public int hashCode() {
            return Objects.hash(info, esq, dir);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    private No raiz = null;

    public void inclua(X i) throws Exception {
        if (i == null) throw new Exception("Informacao Ausente");

        if (this.raiz == null) {
            this.raiz = new No(i);
            return;
        }

        No ant = null, atual = this.raiz;
        int comp;

        do {
            comp = i.compareTo(atual.getInfo());

            if (comp == 0) throw new Exception("Elemento repetido");

            ant = atual;
            if (comp < 0)
                atual = atual.getEsq();
            else
                atual = atual.getDir();
        }
        while (atual != null);

        if (comp < 0)
            ant.setEsq(new No(i));
        else
            ant.setDir(new No(i));
    }




   public boolean remover(X valor){
        //buscar o elemento na árvore
        No atual = this.raiz;
        No paiAtual = null;  

        // aqui espero se o atual for nullo ou se ele for o valor que chegou
        // só vai sair daqui se nao tiver nada ou se localizar o valor
        while(atual != null){
            // vou percorrendo a arvore dessa forma
            if (atual.getInfo().equals(valor)){
                break;      
                //se valor for menor que o atual sei que ele pode estar na esquerda
                // se nao a direita          
            }else if (valor.compareTo(atual.getInfo()) == -1){ //valor procurado é menor que o atual 
                paiAtual = atual;
                atual = atual.getEsq();
            }else{
                paiAtual = atual;
                atual = atual.getDir();
            }
            // sendo assim ao localizar saio do loop sabendo quem é o atual e seu pai
            // nossas variaveis saem daqui ja atribuidas para poder fazer a operação
        }

        // operação da exclusao

        //verifica se o atual existe
        if (atual != null){ 
            //elemento tem 2 filhos ou elemento tem somente filho à direita
            if (atual.getDir() != null){
                // se tenho filho a direita, eu vou para direita 
                // e depois tudo para a esquerda
                // preciso pegar o maior elemento para por no lugar
                No substituto = atual.getDir();
                No paiSubstituto = atual;
                while(substituto.getEsq() != null){
                    // o maior elemento é o ultimo
                    // nesse loop ele vai até la
                    // juntamente com o paiSubstituto
                    paiSubstituto = substituto;
                    substituto = substituto.getEsq();
                }
                substituto.setEsq(atual.getEsq());
                if (paiAtual != null){
                    // coloco no lugar do atual o ultimo valor localizado 
                    // para manter a arvore
                    if (atual.getInfo().compareTo(paiAtual.getInfo()) == -1){ //atual < paiAtual
                        paiAtual.setEsq(substituto);
                    }else{
                        paiAtual.setDir(substituto);
                    }
                }else{ //se não tem paiAtual, então é a raiz
                    this.raiz = substituto;
                    paiSubstituto.setEsq(null);
                    this.raiz.setDir(paiSubstituto);
                    this.raiz.setEsq(atual.getEsq());
                }
                
                //removeu o elemento da árvore
                if (substituto.getInfo().compareTo(paiSubstituto.getInfo()) == -1){ //substituto < paiSubstituto
                    paiSubstituto.setEsq(null);
                }else{
                    paiSubstituto.setDir(null);
                }
                
            }else if (atual.getEsq() != null){ //tem filho só à esquerda
               No substituto = atual.getEsq();
                No paiSubstituto = atual;
                while(substituto.getDir() != null){
                    paiSubstituto = substituto;
                    substituto = substituto.getDir();
                }
                substituto.setDir(atual.getDir());
                if (paiAtual != null){
                    // coloco no lugar do atual o ultimo valor localizado 
                    // para manter a arvore
                    if (atual.getInfo().compareTo(paiAtual.getInfo()) == -1){ // menor sempre esquerda
                        paiAtual.setEsq(substituto);
                    }else{
                        paiAtual.setDir(substituto);
                    }
                }else{ //se for a raiz
                    this.raiz = substituto;
                }
                
                //removeu o elemento da árvore
                if (substituto.getInfo().compareTo(paiSubstituto.getInfo()) == -1){ //substituto < paiSubstituto
                    paiSubstituto.setEsq(null);
                }else{
                    paiSubstituto.setDir(null);
                }
            //Nao tendo filho apenas tiro ele da arvora
            }else{ //não tem filho
                if (paiAtual != null){
                    if (atual.getInfo().compareTo(paiAtual.getInfo()) == -1){ //atual < paiAtual
                        paiAtual.setEsq(null);
                    }else{
                        paiAtual.setDir(null);
                    }
                }else{ //é a raiz
                    this.raiz = null;
                }
            }
            
            return true;
        }else{
            return false;
        }        
    }



    public boolean tem(X x) {
        if (this.raiz == null)
            return false;

        No atual = this.raiz;

        while (atual != null && !x.equals(atual.getInfo())) ;

        if (x.compareTo(atual.getInfo()) < 0)
            atual = atual.getEsq();
        else
            atual = atual.getDir();
                return atual != null;

    }

    private int getQtdNos(No r) {
        if (r == null) return 0;
        else
            return 1 + getQtdNos(r.esq) + getQtdNos(r.dir);
    }

    public int getQtdNos() {
        return this.getQtdNos(this.raiz);
    }

    private String preOrdem(No r) {
        if (r == null) return "";

        return r.getInfo() + " " +
                this.preOrdem(r.getEsq()) + " " +
                this.preOrdem(r.getDir());
    }

    private String inOrdem(No r) {
        if (r == null) return "";

        return this.inOrdem(r.getEsq()) + " " +
                r.getInfo() + " " +
                this.inOrdem(r.getDir());
    }

    private String posOrdem(No r) {
        if (r == null) return "";

        return this.posOrdem(r.getEsq()) + " " +
                this.posOrdem(r.getDir()) + " " +
                r.getInfo();
    }

    public String toString() {
        String pre = this.preOrdem(this.raiz),
                in = this.inOrdem(this.raiz),
                pos = this.posOrdem(this.raiz);

        return "Pré-ordem: " + (pre.equals("") ? "árvore vazia" : pre) + "\n" +
                "In-ordem : " + (in.equals("") ? "árvore vazia" : in) + "\n" +
                "Pós-ordem: " + (pos.equals("") ? "árvore vazia" : pos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arvore<?> arvore = (Arvore<?>) o;
        return Objects.equals(raiz, arvore.raiz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raiz);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
