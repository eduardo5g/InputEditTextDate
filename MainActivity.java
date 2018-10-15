public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText ed = findViewById(R.id.editText);

        ed.addTextChangedListener(new EditTextDateMask(ed,1900,2100));
    }
}
