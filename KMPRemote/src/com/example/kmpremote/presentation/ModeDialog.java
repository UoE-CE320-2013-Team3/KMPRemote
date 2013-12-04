//package com.bignerdranch.android.presentation;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.app.DialogFragment;
//import android.content.DialogInterface;
//import android.os.Bundle;
//
//public class ModeDialog extends DialogFragment {
//	@Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        // Use the Builder class for convenient dialog construction
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setMessage(R.string.mode_slide)
//               .setPositiveButton(R.string.current_slide, new DialogInterface.OnClickListener() {
//                   public void onClick(DialogInterface dialog, int id) {
//                   }
//               })
//               .setNegativeButton(R.string.from_beginning, new DialogInterface.OnClickListener() {
//                   public void onClick(DialogInterface dialog, int id) {
//                   }
//               });
//        return builder.create();
//    }
//}