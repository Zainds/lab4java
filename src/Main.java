import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
    public static class MyTime
    {
        private int hourTime;
        private int minuteTime;
        private int secondTime;

        public MyTime() {};
        public MyTime(int h, int m, int s) {

            this.hourTime = h;
            this.minuteTime = m;
            this.secondTime = s;
        }

        public int GetAllInSeconds(){
            return hourTime * 60 * 60 + minuteTime * 60 + secondTime;
        }
        public void Add5Seconds() {
            secondTime += 5;
            if (secondTime >= 60) {
                minuteTime += 1;
                secondTime -= 60;
                if (minuteTime >= 60) {
                    hourTime += 1;
                    minuteTime -= 60;
                    if (hourTime >=24)
                    {
                        hourTime = 0;

                    }
                }
            }
        }
        public void SetHours(int hours) {
            hourTime = hours;
        }
        public void SetMinutes(int minutes) {
            minuteTime = minutes;
        }
        public void SetSeconds(int Seconds) {
            secondTime = Seconds;
        }

        public int GetHours()  {
        return hourTime;
    }

        public int GetMinutes()  {
        return minuteTime;
    }

        public int GetSeconds()  {
        return secondTime;
    }
        public void PrintTime() {
            String h, m, s;
            if (hourTime < 10) {
                h = "0" + String.valueOf(hourTime);
            }
            else {
                h = "" + String.valueOf(hourTime);
            }
            if (minuteTime < 10) {
                m = "0" + String.valueOf(minuteTime);
            }
            else {
                m = "" + String.valueOf(minuteTime);
            }
            if (secondTime < 10) {
                s = "0" + String.valueOf(secondTime);
            }
            else {
                s = "" + String.valueOf(secondTime);
            }

            System.out.println( h + ":" + m + ":" + s ) ;
        }
        String ReturnFormattedTime() {
            String h, m, s, formattedTime;
            if (hourTime < 10) {
                h = "0" + String.valueOf(hourTime);
            }
            else {
                h = "" + String.valueOf(hourTime);
            }
            if (minuteTime < 10) {
                m = "0" + String.valueOf(minuteTime);
            }
            else {
                m = "" + String.valueOf(minuteTime);
            }
            if (secondTime < 10) {
                s = "0" + String.valueOf(secondTime);
            }
            else {
                s = "" + String.valueOf(secondTime);
            }
            formattedTime = h + " " + m + " " + s;
            return formattedTime;
        }

    };

    static List<MyTime> diskIn() throws FileNotFoundException {
        List<MyTime> arr = new ArrayList<>();
        int arrPos = 0;

        try(FileReader fin = new FileReader("input.txt")) {
            Scanner Reader = new Scanner(fin);
            ArrayList<String> check = new ArrayList<String>();
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                check.add(data);
            }
            System.out.println("Все ОК! Файл открыт!\n\n");

            for (String line : check) {
                StringBuilder hour = new StringBuilder();
                StringBuilder minute = new StringBuilder();
                StringBuilder second = new StringBuilder();
                int spaceCounter = 0;
                if (line != null && !line.trim().isEmpty() /*&& line.charAt(0) != NULL*/) {
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == ' ') spaceCounter += 1;
                        if (spaceCounter == 0) hour.append(line.charAt(i));
                        if (spaceCounter == 1 && line.charAt(i) != ' ') minute.append(line.charAt(i));
                        if (spaceCounter >= 2) {
                            if (line.charAt(i) != ' ') {
                                second.append(line.charAt(i));

                            }

                            if (i == line.length()) {
                                second.append(line.charAt(i));
                            }
                            ;

                        }

                    }
                    MyTime T = new MyTime(Integer.parseInt(hour.toString()), Integer.parseInt(minute.toString()), Integer.parseInt(second.toString()));
                    arr.add(T);
                    arrPos += 1;
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return arr;
    }

    public static void diskOut(List<MyTime> array) throws IOException {
        try(FileWriter fout = new FileWriter("output.txt")){
            for(MyTime x: array){
                fout.write(x.ReturnFormattedTime() + "\n");
            }



        }

    }
    public static void main(String[] args) throws IOException {

        MyTime T = new MyTime(23, 59, 50);

        /*T.PrintTime();
        T.Add5Seconds();
        T.PrintTime();
        T.Add5Seconds();
        T.PrintTime();
        T.Add5Seconds();
        T.PrintTime();
        System.out.println(T.GetAllInSeconds());*/
        List<MyTime> a = diskIn();
        for (MyTime x : a) {
            x.PrintTime();
        }
        diskOut(a);
    }
}