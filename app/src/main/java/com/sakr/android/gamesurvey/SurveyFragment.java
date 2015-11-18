package com.sakr.android.gamesurvey;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class SurveyFragment extends Fragment
{
    public static final String Q_1_ANSWER ="answer1";
    public static final String Q_2_ANSWER ="answer2";
    public static final String Q_3_ANSWER ="answer3";
    public static final String Q_4_ANSWER ="answer4";

    private boolean isQ1Answered,isQ2Answered,isQ3Answered,isQ4Answered = false;
    private TextView mAnswerExcellent1, mAnswerGood1, mAnswerFair1, mAnswerPoor1;

    private TextView mAnswerExcellent2, mAnswerGood2, mAnswerFair2, mAnswerPoor2;

    private TextView mAnswerExcellent3, mAnswerGood3, mAnswerFair3, mAnswerPoor3;

    private TextView mAnswerExcellent4, mAnswerGood4, mAnswerFair4, mAnswerPoor4;

    private TextView mAnswerQ1, mAnswerQ2, mAnswerQ3, mAnswerQ4;

    private Button mBarButton;

    public SurveyFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_survey, container, false);

        // views to drag
        mAnswerExcellent1 = (TextView) root.findViewById(R.id.answerExcellent1);
        mAnswerGood1 = (TextView) root.findViewById(R.id.answerGood1);
        mAnswerFair1 = (TextView) root.findViewById(R.id.answerFair1);
        mAnswerPoor1 = (TextView) root.findViewById(R.id.answerPoor1);

        mAnswerExcellent1.setOnTouchListener(new SurveyTouchListener());
        mAnswerGood1.setOnTouchListener(new SurveyTouchListener());
        mAnswerFair1.setOnTouchListener(new SurveyTouchListener());
        mAnswerPoor1.setOnTouchListener(new SurveyTouchListener());

        mAnswerExcellent2 = (TextView) root.findViewById(R.id.answerExcellent2);
        mAnswerGood2 = (TextView) root.findViewById(R.id.answerGood2);
        mAnswerFair2 = (TextView) root.findViewById(R.id.answerFair2);
        mAnswerPoor2 = (TextView) root.findViewById(R.id.answerPoor2);

        mAnswerExcellent2.setOnTouchListener(new SurveyTouchListener());
        mAnswerGood2.setOnTouchListener(new SurveyTouchListener());
        mAnswerFair2.setOnTouchListener(new SurveyTouchListener());
        mAnswerPoor2.setOnTouchListener(new SurveyTouchListener());

        mAnswerExcellent3 = (TextView) root.findViewById(R.id.answerExcellent3);
        mAnswerGood3 = (TextView) root.findViewById(R.id.answerGood3);
        mAnswerFair3 = (TextView) root.findViewById(R.id.answerFair3);
        mAnswerPoor3 = (TextView) root.findViewById(R.id.answerPoor3);

        mAnswerExcellent3.setOnTouchListener(new SurveyTouchListener());
        mAnswerGood3.setOnTouchListener(new SurveyTouchListener());
        mAnswerFair3.setOnTouchListener(new SurveyTouchListener());
        mAnswerPoor3.setOnTouchListener(new SurveyTouchListener());

        mAnswerExcellent4 = (TextView) root.findViewById(R.id.answerExcellent4);
        mAnswerGood4 = (TextView) root.findViewById(R.id.answerGood4);
        mAnswerFair4 = (TextView) root.findViewById(R.id.answerFair4);
        mAnswerPoor4 = (TextView) root.findViewById(R.id.answerPoor4);

        mAnswerExcellent4.setOnTouchListener(new SurveyTouchListener());
        mAnswerGood4.setOnTouchListener(new SurveyTouchListener());
        mAnswerFair4.setOnTouchListener(new SurveyTouchListener());
        mAnswerPoor4.setOnTouchListener(new SurveyTouchListener());

        //views to drop onto

        mAnswerQ1 = (TextView) root.findViewById(R.id.answerQ1);
        mAnswerQ2 = (TextView) root.findViewById(R.id.answerQ2);
        mAnswerQ3 = (TextView) root.findViewById(R.id.answerQ3);
        mAnswerQ4 = (TextView) root.findViewById(R.id.answerQ4);

        mAnswerQ1.setOnDragListener(new SurveyDragListener());
        mAnswerQ2.setOnDragListener(new SurveyDragListener());
        mAnswerQ3.setOnDragListener(new SurveyDragListener());
        mAnswerQ4.setOnDragListener(new SurveyDragListener());

        //bar chart button
        mBarButton = (Button)root.findViewById(R.id.barButton);
        mBarButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if( isQ1Answered && isQ2Answered && isQ3Answered && isQ4Answered)
                {
                    Intent intent = new Intent(getActivity(),BarChartActivity.class);
                    intent.putExtra(Q_1_ANSWER,getAnswerValue(mAnswerQ1.getText().toString()));
                    intent.putExtra(Q_2_ANSWER,getAnswerValue(mAnswerQ2.getText().toString()));
                    intent.putExtra(Q_3_ANSWER,getAnswerValue(mAnswerQ3.getText().toString()));
                    intent.putExtra(Q_4_ANSWER,getAnswerValue(mAnswerQ4.getText().toString()));
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getActivity(), R.string.toast_msg,Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    } // end method onCreateView

    private final class SurveyTouchListener implements View.OnTouchListener
    {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent)
        {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
            {
                //setup drag
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                //start dragging the answer
                view.startDrag(data, shadowBuilder, view, 0);

                return true;
            } else
            {
                return false;
            }
        }
    } // end class


    private class SurveyDragListener implements View.OnDragListener
    {

        @Override
        public boolean onDrag(View view, DragEvent event)
        {
            //handle drag events
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a target view
                    TextView dragged = (TextView) event.getLocalState();
                    TextView target = (TextView)view;

                    if (getQuestionNumber(dragged) == 1 && getAnswerNumber(target) == 1)
                    {
                        target.setText(dragged.getText());
                        target.setTypeface(Typeface.DEFAULT_BOLD);
                        isQ1Answered = true;
                    }
                    else if (getQuestionNumber(dragged) == 2 && getAnswerNumber(target) == 2)
                    {
                        target.setText(dragged.getText());
                        target.setTypeface(Typeface.DEFAULT_BOLD);
                        isQ2Answered = true;
                    }
                    else if (getQuestionNumber(dragged) == 3 && getAnswerNumber(target) == 3)
                    {
                        target.setText(dragged.getText());
                        target.setTypeface(Typeface.DEFAULT_BOLD);
                        isQ3Answered = true;
                    }
                    else if (getQuestionNumber(dragged) == 4 && getAnswerNumber(target) == 4)
                    {
                        target.setText(dragged.getText());
                        target.setTypeface(Typeface.DEFAULT_BOLD);
                        isQ4Answered = true;
                    }



                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }

            return true;
        }
    } // end class

    private int getQuestionNumber(TextView view)
    {
        switch (view.getId())
        {
            case R.id.answerExcellent1:
                return 1;

            case R.id.answerGood1:
                return 1;
            case R.id.answerFair1:
                return 1;

            case R.id.answerPoor1:
                return 1;

            //question 2
            case R.id.answerExcellent2:
                return 2;

            case R.id.answerGood2:
                return 2;
            case R.id.answerFair2:
                return 2;

            case R.id.answerPoor2:
                return 2;

            //question 3
            case R.id.answerExcellent3:
                return 3;

            case R.id.answerGood3:
                return 3;
            case R.id.answerFair3:
                return 3;

            case R.id.answerPoor3:
                return 3;

            //question 4
            case R.id.answerExcellent4:
                return 4;

            case R.id.answerGood4:
                return 4;
            case R.id.answerFair4:
                return 4;

            case R.id.answerPoor4:
                return 4;
            default:
                return 0;
        }
    } // end method getQuestionNumber

    private int getAnswerNumber(TextView view)
    {
        switch (view.getId())
        {
            case R.id.answerQ1:
                return 1;

            case R.id.answerQ2:
                return 2;

            case R.id.answerQ3:
                return 3;

            case R.id.answerQ4:
                return 4;
            default:
                return 0;
        }
    } // end method getAnswerNumber

    private int getAnswerValue(String answer)
    {
        if (answer.equals(getString(R.string.excellent_text)))
        {
            return 20;
        }
        else if (answer.equals(getString(R.string.good_text)))
        {
            return 15;
        }
        else if (answer.equals(getString(R.string.fair_text)))
        {
            return 10;
        }
        else if (answer.equals(getString(R.string.poor_text)))
        {
            return 5;
        }
        return 0;
    }

} // end class SurveyFragment
