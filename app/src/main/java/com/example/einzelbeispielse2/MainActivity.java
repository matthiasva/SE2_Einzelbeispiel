package com.example.einzelbeispielse2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView2;
    TextView textView;
    EditText editText;
    Button buttonSend;
    Button buttonCalculate;
    TCPClient tcpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSend = findViewById(R.id.button);
        buttonSend.setOnClickListener(this);

        buttonCalculate = findViewById(R.id.button2);
        buttonCalculate.setOnClickListener(this);

        editText = findViewById(R.id.editTextNumber);
        textView2 = findViewById(R.id.textView2);


        tcpClient = new TCPClient();
        tcpClient.execute();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            editText.setText(tcpClient.message);
            textView2.setText(tcpClient.modifiedMessage);
        }
        if (view.getId() == R.id.button2) {
            int matNr = Integer.parseInt(editText.getText().toString());
            String n = editText.getText().toString();
            int matNrMod = matNr % 7;
            String matNrModString = String.valueOf(matNrMod);
            textView = findViewById(R.id.textView3);
            textView.setText("% 7 = "+matNrModString);

            String temp = Integer.toString(matNr);
            String[] arrayString = new String[temp.length()];
            for (int i = 0; i < temp.length(); i++) {
                arrayString[i] = String.valueOf(temp.charAt(i));
            }
            int[] arrayInt = new int[temp.length()];
            for (int i = 0; i < temp.length(); i++) {
                arrayInt[i] = Integer.parseInt(arrayString[i]);
            }
            int countEven = 0;
            int countOdd = 0;
            for (int i = 0; i < arrayString.length; i++) {
                if (Integer.parseInt(arrayString[i]) % 2 == 0) {
                    countEven++;
                }else {
                    countOdd++;
                }
            }

            String[] arrayEven = new String[countEven];
            String[] arrayOdd = new String[countOdd];
            int j = 0;
            int k = 0;
            for (int i = 0; i < temp.length(); i++) {
                if (Integer.parseInt(arrayString[i]) % 2 == 0) {
                    arrayEven[j] = String.valueOf(temp.charAt(i));
                    j++;
                }else {
                    arrayOdd[k] = String.valueOf(temp.charAt(i));
                    k++;
                }
            }

            switch (matNrMod) {

                case 0:
                    int sum = 0;
                    for(int i = 0; i<n.length(); i++) {
                        if(i%2==0)
                            sum+=Integer.parseInt(String.valueOf(n.charAt(i)));
                        else
                            sum-=Integer.parseInt(String.valueOf(n.charAt(i)));
                    }
                    if (sum % 2 == 0) {
                        String output = String.valueOf(sum);
                        textView2.setText(output+"\nGerade");
                    }else {
                        String output = String.valueOf(sum);
                        textView2.setText(output+"\nUngerade");
                    }
                    break;

                case 1:
                    Arrays.sort(arrayEven);
                    Arrays.sort(arrayOdd);
                    textView2.setText(Arrays.toString(arrayEven)+"\n"+Arrays.toString(arrayOdd));
                    break;

                case 2:
                    int[] arrayTeiler = new int[2];
                    for (int i = 0; i < arrayInt.length; i++) {
                        for (int x = 0; x < arrayInt.length; x++) {
                            for (int y = 2; y < 10; y++) {
                            if ((arrayInt[i] % y == 0) && (arrayInt[x] % y == 0) && (i != x)) {
                                arrayTeiler[0] = x;
                                arrayTeiler[1] = i;
                            }
                        }
                        }
                    }
                    textView2.setText(Arrays.toString(arrayTeiler));
                    break;

                case 3:
                    char[] arrayChar = new char[temp.length()];
                    for (int i = 0; i < arrayInt.length; i++) {
                        if (i % 2 != 0) {
                            arrayChar[i] = (char) (arrayInt[i]+96);
                        }else {
                            arrayChar[i] = (char) (arrayInt[i]+48);
                        }
                    }
                    textView2.setText(Arrays.toString(arrayChar));
                    break;

                case 4:
                    int quersumme = 0;
                    String binary = "";
                    for (int i = 0; i < temp.length(); i++) {
                        quersumme += arrayInt[i];
                    }
                    binary = Integer.toBinaryString(quersumme);
                    textView2.setText("Quersumme: "+String.valueOf(quersumme)+"\nBinär: "+binary);
                    break;

                case 5:
                    Arrays.sort(arrayInt);
                    int count = 0;
                    for (int i = 0; i < arrayInt.length; i++) {
                        if (!isPrime(arrayInt[i])) {
                            count++;
                        }
                    }
                    int[] array = new int[count];
                    int x = 0;
                    for (int i = 0; i < arrayInt.length; i++) {
                        if (!isPrime(arrayInt[i])) {
                            array[x] = arrayInt[i];
                            x++;
                        }
                    }
                    textView2.setText(Arrays.toString(array));
                    break;

                case 6:
                    Arrays.sort(arrayInt);
                    int countPrime = 0;
                    for (int i = 0; i < arrayInt.length; i++) {
                        if (isPrime(arrayInt[i])) {
                            countPrime++;
                        }
                    }

                    int[] arrayPrime = new int[countPrime];
                    int y = 0;
                    for (int i = 0; i < arrayInt.length; i++) {
                        if (isPrime(arrayInt[i])) {
                            arrayPrime[y] = arrayInt[i];
                            y++;
                        }
                    }
                    textView2.setText(Arrays.toString(arrayPrime));
            }

        }
    }

    public static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;
        return true;
    }
}


