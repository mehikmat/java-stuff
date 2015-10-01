package xample;

import org.junit.Test;
import xample.generated.Student;

import java.io.*;


/**
 * Unit test for simple App.
 */
public class AppTest{

    @Test
    public void protobuffTest() throws IOException, ClassNotFoundException {
        String fileDB="res/file_db";
        File file=new File(fileDB);
        Student.StudentInfo.Builder student=Student.StudentInfo.newBuilder();
        student.setId("1");
        student.setName("Hikmat");


        FileOutputStream outputStream=new FileOutputStream(file);
        student.build().writeTo(outputStream);
        outputStream.close();

        FileInputStream inputStream =new FileInputStream(file);

        Student.StudentInfo studentInfo=Student.StudentInfo.parseFrom(inputStream);


        System.out.printf("Name:"+studentInfo.getName());

        inputStream.close();

    }


}
