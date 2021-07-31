package client1;

import POJO.Job;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.*;

@Component
public class restApp implements CommandLineRunner {





    public static void callTask5(){
        RestTemplate temp = new RestTemplate();
        ResponseEntity<List<String[]>> responseEntity= temp.exchange("http://localhost:8080/sum",HttpMethod.GET,null,
                new ParameterizedTypeReference<List<String[]>>() {});
        List<String[]> jobs = responseEntity.getBody();
        System.out.println("Task 0 : Summary ");
        for( String[] j : jobs) {
            for (String s : j) {
                System.out.print("| "+s+" |");
            }
            System.out.println("");
        }
    }
    public void callTask(int t) throws IOException {
        RestTemplate temp = new RestTemplate();
        ResponseEntity<List<Job>> responseEntity= temp.exchange("http://localhost:8080/t"+t,HttpMethod.GET,null,
                new ParameterizedTypeReference<List<Job>>() {} );
        List<Job> jobs = responseEntity.getBody();
        for( Job j : jobs) {System.out.println(j); }
        /*try {
            Image picture = ImageIO.read(new File("s1.png"));
            showImage(picture);
        } catch (IOException ex) {
        }*/

    }
    public void showImage(Image picture){
        JFrame editorFrame = new JFrame("Image Demo");
        editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BufferedImage image = null;
        ImageIcon imageIcon = new ImageIcon(picture);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

        editorFrame.pack();
        editorFrame.setLocationRelativeTo(null);
        editorFrame.setVisible(true);
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("*********************************************");
        System.out.println("**              Data Summary               **");
        System.out.println("*********************************************");
        callTask5();
        System.out.println("*********************************************");
        System.out.println("**Task 1 : Count the jobs for each company **");
        System.out.println("*********************************************");
        callTask(1);
        System.out.println("*********************************************");
        System.out.println("**Task 2 : Count the jobs for each Title   **");
        System.out.println("*********************************************");
        callTask(2);
        System.out.println("*********************************************");
        System.out.println("**Task 3 : Most required skills            **");
        System.out.println("*********************************************");
        callTask( 3);
        System.out.println("*********************************************");
        System.out.println("**Task 4 : Count the jobs for each Country **");
        System.out.println("*********************************************");
        callTask(4);
        System.out.println("*********************************************");
        System.out.println("**          Program Finished               **");
        System.out.println("*********************************************");
    }
}
