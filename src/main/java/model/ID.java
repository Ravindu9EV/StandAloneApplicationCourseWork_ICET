package model;

public class ID implements Comparable<ID>{
    private  String id;

      public ID(String id){
        this.id=id;
    }



    @Override
    public int compareTo(ID o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public String toString(){return this.id;}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ID? ((ID)obj).id.equals(this.id):false;
    }

    @Override
    public int hashCode(){
        return this.id.hashCode();
    }
}
