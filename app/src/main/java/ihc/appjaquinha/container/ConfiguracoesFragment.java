package ihc.appjaquinha.container;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import ihc.appjaquinha.R;

import static ihc.appjaquinha.auth.LoginActivity.dateToString;
import static ihc.appjaquinha.auth.LoginActivity.stringToDate;
import static ihc.appjaquinha.container.ContainerActivity.user;

public class ConfiguracoesFragment extends Fragment {
    private DatabaseReference mDatabase;
    private EditText nomeText, dataText, sexoText, pesoText, alturaText;
    private DatePickerDialog data_Dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return inflater.inflate(R.layout.fragment_configuracoes, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nomeText = view.findViewById(R.id.nome);
        dataText = view.findViewById(R.id.data);
        sexoText = view.findViewById(R.id.sexo);
        pesoText = view.findViewById(R.id.peso);
        alturaText = view.findViewById(R.id.altura);

        if (user != null) {
            nomeText.setText(user.getUsername());
            dataText.setText(user.getNascimento());
            sexoText.setText(user.getSexo());
            pesoText.setText(String.valueOf(user.getPeso()));
            alturaText.setText(String.valueOf(user.getAltura()));
        }

        Calendar data_atual = Calendar.getInstance();
        data_Dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar dataPicker = Calendar.getInstance();
                dataPicker.set(year, monthOfYear, dayOfMonth);
                dataText.setText(dateToString(dataPicker, "dd/MM/yyyy"));
            }

        }, data_atual.get(Calendar.YEAR)-20, data_atual.get(Calendar.MONTH), data_atual.get(Calendar.DAY_OF_MONTH));
        data_Dialog.getDatePicker().setMaxDate(data_atual.getTimeInMillis());
        if(!dataText.getText().toString().isEmpty()) {
            Calendar aux = stringToDate(dataText.getText().toString(), "dd/MM/yyyy");
            data_Dialog.updateDate(aux.get(Calendar.YEAR), aux.get(Calendar.MONTH), aux.get(Calendar.DAY_OF_MONTH));
        }
        dataText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_Dialog.show();
            }
        });

        view.findViewById(R.id.atualizarbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                String nome = nomeText.getText().toString();
                String data = dataText.getText().toString();
                String sexo = sexoText.getText().toString();
                float peso = Float.parseFloat(pesoText.getText().toString());
                int altura = Integer.parseInt(alturaText.getText().toString());

                user.SetConfiguracoes(nome, data, sexo, peso, altura);
                mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
            }
        });
    }
}