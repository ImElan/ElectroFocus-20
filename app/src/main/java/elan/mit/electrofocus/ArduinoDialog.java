package elan.mit.electrofocus;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import elan.mit.electrofocus.R;

public class ArduinoDialog extends AppCompatDialogFragment
{
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private ArduinoDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.arduino_dialog,null);

        radioGroup = view.findViewById(R.id.radio_group);

        builder.setView(view)
                .setNegativeButton("Cancel",null)
                .setPositiveButton("Continue To Register", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int radioId = radioGroup.getCheckedRadioButtonId();
                        radioButton = view.findViewById(radioId);
                        int index = radioGroup.indexOfChild(radioButton);
                        listener.ApplyText(index);
                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try
        {
            listener = (ArduinoDialogListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString() + "Error:Must implement Custom Dialog");
        }
    }

    public interface ArduinoDialogListener
    {
        void ApplyText(int index);
    }
}
