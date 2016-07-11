package zaihuishou.com.expandablerecyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zaihuishou.expandablerecycleradapter.Adapter.BaseRcvAdapter;
import com.zaihuishou.expandablerecycleradapter.ViewHolder.AbstractAdapterItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BaseRcvAdapter mBaseRcvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rcv);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        List companyList = new ArrayList<>();

        companyList.add(creaateCompany("天上人间"));

        Department department = new Department();
        department.name = "测试部门:";
        companyList.add(department);

        companyList.add(creaateCompany("地狱之火"));
        mBaseRcvAdapter = new BaseRcvAdapter(companyList) {
            @NonNull
            @Override
            public AbstractAdapterItem<Object> getItemView(Object type) {
                int type1 = (int) type;
                switch (type1) {
                    case 1:
                        CompanyItem companyItem = new CompanyItem();
//                        companyItem.setExpanded(true);
                        return companyItem;
                    case 2:
                        return new DepartmentItem();
                }
                return null;
            }

            @Override
            public Object getItemViewType(Object t) {
                if (t instanceof Company) {
                    return 1;
                } else if (t instanceof Department)
                    return 2;
                else return 3;
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mBaseRcvAdapter);
    }

    @NonNull
    private Company creaateCompany(String companyName) {
        Company firstCompany = new Company();
        firstCompany.name = companyName;

        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Department department = new Department();
            department.name = "一级子部门:" + i;
            departments.add(department);
        }
        firstCompany.mDepartments = departments;
        return firstCompany;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
