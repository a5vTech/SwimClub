import java.util.ArrayList;
public class test {
    public static void main(String[] args) {
        ArrayList<Employee> employeeList = new ArrayList<>();


        Employee e1 = new Manager("Jesper","Petersen","keavej 21 2200 by",29903900);
        employeeList.add(e1);

        //System.out.println(employeeList);

        System.out.println("");

      /*  for(Employee m1: employeeList){
            if(m1.getPay() == false){ //Check if Employee's have payed
                String name = m1.getName();
                Boolean pay = m1.getPay();
                System.out.printf("Name: %s %s\n",name,pay);
            }
        } */

        for(Employee e: employeeList){
            if(e instanceof Cashier){ //Check if employee's a Cashier
                System.out.println("Cashier");
            }
            if(e instanceof Manager){ //Check if employee's a Manager
                System.out.println("Manager");
                System.out.println(e.getName()+" "+e.getTitle()+" "+e.getPhoneNumber());
            }
        }
    }
}
