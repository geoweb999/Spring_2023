public interface InterfaceB {
   void methodB();
   default void methodX() {  System.out.println("interfaceB, methodX");  }
}