package com.bozidar.microdroid.widget;

import android.app.AlertDialog;
import android.content.DialogInterface;

import rx.Observable;
import rx.Subscriber;

/**
 * Class that represents Custom AlertDIalog implemented by RxJava
 */
public class MicroAlertDialog implements Observable.OnSubscribe<Integer> {

    private final AlertDialog.Builder builder;
    private final String positiveButton;
    private final String negativeButton;

    public MicroAlertDialog(AlertDialog.Builder builder, String positiveButton, String negativeButton) {
        this.builder = builder;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
    }

    @Override
    public void call(final Subscriber<? super Integer> subscriber) {
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                subscriber.onNext(which);
                subscriber.onCompleted();
            }
        };

        if (positiveButton != null) {
            builder.setPositiveButton(positiveButton, listener);
        }
        if (negativeButton != null) {
            builder.setNegativeButton(negativeButton, listener);
        }

        builder.create().show();

    }
}
