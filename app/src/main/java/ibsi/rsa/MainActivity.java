package ibsi.rsa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TextView publicKey;
    private TextView privateKey;
    private TextView encryptMessage;
    private TextView decryptMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked();
            }
        });
    }

    private void init() {
        editText = (EditText) this.findViewById(R.id.textMessage);
        button = (Button) this.findViewById(R.id.button);
        publicKey = (TextView) this.findViewById(R.id.publicKey);
        privateKey = (TextView) this.findViewById(R.id.privateKey);
        encryptMessage = (TextView) this.findViewById(R.id.encryptMessage);
        decryptMessage = (TextView) this.findViewById(R.id.decryptMessage);
    }

    private void clicked() {
        int N = 32;
        RSA key = new RSA(N);
        publicKey.setText(key.getPublicKey().toString());
        privateKey.setText(key.getPrivateKey().toString());

        key.setMessage(editText.getText().toString());

        String encrypt = key.encrypt(); //получаем зашифрованную строку
        key.setEncryptMessage(encrypt);
        String decrypt = key.decrypt(); //дешифруем

        encryptMessage.setText(encrypt);
        decryptMessage.setText(decrypt);
    }
}
