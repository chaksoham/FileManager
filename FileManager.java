import java.io.*;
import java.util.*;


public class FileManager
{


   
    int file_number;
    String file_name;
    String temp_file_name;



    void set_file_name(int file_number)
    {
        //could be generalised as pro + char(i) +.properties 
        
       
        if(file_number == 1)
        {
            file_name="proA.properties";
            temp_file_name="proA.temp";
            key_type="MD";
            val_type="Des"
        }
        else if(file_number == 2)
        {
            file_name="proB.properties";
            temp_file_name="proB.temp";
        }
        else
        {
            file_name="proC.properties";
            temp_file_name="proC.temp";
        }


    }

    void setFileNumber(int file_no)
    {
        file_number=file_no;
        set_file_name(file_number);
        
       /* try{
                in = new FileReader(file_name);
                //out=new FileWriter(file_name);
            }
        catch(Exception e)
            {
                e.printStackTrace();
            }
        */
       
    }

    void insert(String key,String value)
    {   
        System.out.println("Ïnsertint to File"+file_name);
        try{
                String s=key + " "+value;
        
                FileWriter fw = new FileWriter(file_name,true); //the true will append the new data
                fw.write(s+"\n");//appends the string to the file
                fw.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

        
    }

    String parse_line_key(String str)
    {
            String res;
            int i=0;
          for( i=0;i<str.length();i++)
          {
                if(str.charAt(i)==' ')
                    break;

          }  

          res=str.substring(0,i);
          return res;
    }

     String parse_line_value(String str)
    {
            String res;
            int i=0;
          for( i=0;i<str.length();i++)
          {
                if(str.charAt(i)==' ')
                    break;

          }  

          res=str.substring(i,str.length());
          return res;
    }


    void delete(String key)
    {

        try {
        // Open a temporary file to write to.
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp_file_name)));
                Scanner scanner = new Scanner(file_name);
                while (scanner.hasNextLine()) 
                {
                    String lineFromFile = scanner.nextLine();
                    String key_from_file=parse_line_key(lineFromFile);

                    if(key_from_file.equals(key))
                    {
                        //update that key value
                        System.out.println("Key found and is been deleted");

                    }
                    else
                    {
                        writer.println(lineFromFile);

                    }  
                }
                writer.close();

                //done with old file

                File realName = new File(file_name);
                realName.delete(); // remove the old file
                new File(temp_file_name).renameTo(realName); // Rename temp file
            }
            catch(Exception e)   
            {
                e.printStackTrace();
            }   
    }



    //update file
    void update(String key, String new_val)
    {
           try {
        // Open a temporary file to write to.
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp_file_name)));
                File ff=new File(file_name);

                Scanner scanner = new Scanner(ff);
                while (scanner.hasNextLine()) 
                {
                    String lineFromFile = scanner.nextLine();
                    String key_from_file=parse_line_key(lineFromFile);
                    String val_from_file=parse_line_value(lineFromFile);

                    if(key_from_file.equals(key))
                    {
                        //update that key value
                        System.out.println("Key found and is been updated ");
                        System.out.println("key ::"+key_from_file);
                        System.out.println("Öld value ::"+val_from_file);
                        System.out.println("New value ::"+new_val);
                        String ss=key + " "+new_val;

                        writer.println(ss);

                    }
                    else
                    {
                        writer.println(lineFromFile);

                    }  
                }
                writer.close();

                //done with old file

                 File realName = new File(file_name);
                realName.delete(); // remove the old file
                new File(temp_file_name).renameTo(realName); // Rename temp file
            }
            catch(Exception e)   
            {
                e.printStackTrace();
            }   
    }


    //read file from top to bottom as key value pair
    void read()
    {   
        System.out.println("Reading "+file_name);

        try{
                File ff=new File(file_name);
                Scanner scanner = new Scanner(ff);
                while (scanner.hasNextLine()) 
                {        String s=scanner.nextLine();
                         String key=parse_line_key(s);
                         String val=parse_line_value(s);
                         System.out.println("key ::"+key);
                         System.out.println("value ::"+val);
                         System.out.println("-----------------");

                     
                }
            }
            catch(Exception e)
            {
                 e.printStackTrace();      
            }
    }


// All starts here !!!
public static void main(String args[])throws IOException
{

      int file_number=0;
      int operation_number=0;
      int flag=1;
      String key;
      String value;

     
      FileManager fm =new FileManager();

      // taking Input 
     InputStreamReader inp= new InputStreamReader(System.in);
     BufferedReader br= new BufferedReader(inp);

     System.out.println("######### Welcome to File Management System #############");
     
     do
     {
         System.out.println(" Please Enter option 1 : Action on File 1 2: Action on File 2 3: Action on File 3 0:Exit");
         file_number = Integer.parseInt(br.readLine());
         if(file_number!=0)
         { 
                fm.setFileNumber(file_number);

             for(int i=0 ;i<10;i++)
             {
                System.out.println(" Please Enter option 1 : Insert a new value 2: Delete a value 3: Update a value 4: Read file //Anything Exit ");
                operation_number = Integer.parseInt(br.readLine());

                switch(operation_number)
                {


                case 1:
                     //insert

                    System.out.println(" Insert key  to enter ");
                    
                    key=br.readLine();
                     System.out.println(" Insert value to enter ");

                    value=br.readLine();

                    fm.insert(key,value);
                    break;

                case 2:   
                    //delete

                    System.out.println(" Delete key enter");
                    
                    key=br.readLine();

                    fm.delete(key);

                     break;
                case 3:
                    //update

                    System.out.println(" Update key enter");
                    
                    key=br.readLine();

                    System.out.println(" Update value enter");
                    value=br.readLine();

                    fm.update(key,value);
                    break;

                case 4:
                    //read

                
                    fm.read();

                    break;

                default:
                  System.out.println("bye");
                  i=10;


                }
            } 
      }     


    }while(file_number !=0);

     System.out.println("Thanks for reviewing my profile: ");
}     

}