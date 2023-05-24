public interface InterfaceA {
   void methodA();
   default void methodX() {   System.out.println("interfaceA, methodX");  }
}