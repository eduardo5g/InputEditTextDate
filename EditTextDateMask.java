public class EditTextDateMask implements TextWatcher {

//    Calendar will validated the date
    private Integer minYear = 1900;
    private Integer maxYear = 2100;
    private Calendar cal = Calendar.getInstance();
    private String txt1="",txt2="",strDate="";
    private EditText input;

    public EditTextDateMask(EditText input) {
        this.input = input;
        this.input.addTextChangedListener(this);
    }
    public EditTextDateMask(EditText input, Integer _minYear, Integer _maxYear) {
        this.minYear = _minYear;
        this.maxYear = _maxYear;
        this.input = input;
        this.input.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        txt1 = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
    @Override
    public void afterTextChanged(Editable s) {
//      To name two strings for better visualization
        txt2 = s.toString();
//        Begin edition in string date
//        if txt2 is greater than txt1 then one number does add
        if (!strDate.equals(txt2)) {
//            Avoid loops in cod
            if (txt2.length()>1 && txt2.substring(txt2.length()-1).equals("/")) return;
            if (txt1.length() < txt2.length()) {
                strDate = MoreNumber(txt2);
                CheckDate(strDate);
                return;
            }
        }
//        if txt2 is less than txt1 then backspace
        if (!txt2.equals(strDate)){
//            Avoid loops in cod
            if (txt1.length()>1 && txt1.substring(txt1.length()-1).equals("/")) return;
            if (txt1.length() > txt2.length()) {
                strDate=BackSpace(txt2);
                CheckDate(strDate);
                return;
            }
        }
   }
//            Set String Format end position cursor
    private void CheckDate(String sDate){

        int day = (sDate.length()>2)? parseInt(sDate.substring(0,2)):1;
        int mon = (sDate.length()>=5)? parseInt(sDate.substring(3,5)):1;
        int year = (sDate.length()>9)? parseInt(sDate.substring(7,10)):minYear;
        year = ( year < minYear ) ? 1900 : ( year > 2100 ) ? 2100:year;

        if ( sDate.length()>=5 ) {
//            check if month have days
            cal.set(Calendar.MONTH, mon-1);
            cal.set(Calendar.YEAR, year);
            day = ( day > cal.getActualMaximum(Calendar.DATE) ) ? cal.getActualMaximum(Calendar.DATE) : day;
//            insert '0' with default date
            sDate = (day > 10)? day + sDate.substring(2): "0" + day + sDate.substring(2);
        }
        input.setText(sDate);
        input.setSelection(sDate.length());
    }
    private String BackSpace(String txt){
        switch (txt.length()) {
            case 3: // txt = 00/
                return txt.substring(0,1);
            case 6: // txt = 00/00/
                return txt.substring(0,4);
            default:
                return txt;
        }
    }
    private String MoreNumber(String txt){
        switch (txt.length()) {
            case 1:
//                If first number greater than 3 then dont is day of mount
                return (parseInt(txt) > 3) ? "31/" : txt;
            case 2:
//                If first number greater than 31 then dont is day of mount
                return (parseInt(txt) > 31) ? "31/" : txt + "/";
            case 3:
//                Here is '/' then skip this
                return txt;
            case 4:
//                If first number of mount is greater than 1 then don't is mount
//                Complete and pass for year
                return  (parseInt(txt.substring(3, 4)) > 1) ? txt.substring(0, 3) + "0" + txt.substring(3, 4) + "/" : txt;
            case 5:
//                if first number of mount is greater than 12 then don't is mount
//                Complete and pass for year
                return (parseInt(txt.substring(3, 5)) > 12) ? txt.substring(0, 3) + "12/" : txt + "/";
            case 6:
//                Here is '/' then skip this
                return txt;
            case 7:
                if (parseInt(txt.substring(6, 7)) > 2) {
                    return txt.substring(0, 6) + "19" + txt.substring(6,7);
                } else{
                    return txt;
                }
            case 8:
                if (parseInt(txt.substring(6, 8)) > 20) {
                    return txt.substring(0, 6) + "20" + txt.substring(7,8);
                } else{
                    return txt;
                }
            default:
                if(txt.length()>8){
                    return txt;
                }
                break;
        }
        return txt;
    }
}