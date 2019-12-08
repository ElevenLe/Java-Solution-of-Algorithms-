import sun.text.normalizer.UBiDiProps;

public class Pric {
    RichPeople zhao = new RichPeople("zhao");
    RichPeople.Helicopter zhaojet = zhao.new Helicopter();

    UberUser.Helicopter share = new UberUser.Helicopter();

}

class RichPeople{
    String name;

    public RichPeople(String n){
        name = n;
    }


    // inner class
    class Helicopter{
        // 内部类里面的参数可以访问外部类的参数
        // String label = name；
        // 如果两个参数名字一样，必须使用 this 来访问。
        String name = RichPeople.this.name;
    }
}

class UberUser{
    String name;
    static class Helicopter{
        // String label = name; 是不行的。因为static 不能用instance的 参数
        // name 必须有instance 才能有，所以static 不能使用
    }
}