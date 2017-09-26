package com.rustem.tester;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class MyAlertDialog extends DialogFragment {
    private AlertDialogListener mListener;

    public void setmListener(AlertDialogListener mListener) {
        this.mListener = mListener;
    }

    public MyAlertDialog() {
    }

    interface AlertDialogListener {
        void onPositiveClick();
    }

    public static MyAlertDialog newInstance(AlertDialogListener mListener, int totalGuesses) {
        MyAlertDialog alertDialog = new MyAlertDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("totalGuesses", totalGuesses);
        alertDialog.setArguments(bundle);
        alertDialog.setmListener(mListener);
        return alertDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        int mTotalGuesses = getArguments().getInt("totalGuesses", 0);

        android.app.AlertDialog.Builder builder =
                new android.app.AlertDialog.Builder(getActivity());
        builder.setMessage(
                getString(R.string.results,
                        mTotalGuesses,
                        (1000 / (double) mTotalGuesses)));

        builder.setPositiveButton(R.string.reset_quiz,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onPositiveClick();
                    }
                }
        );

        return builder.create();
    }
}
