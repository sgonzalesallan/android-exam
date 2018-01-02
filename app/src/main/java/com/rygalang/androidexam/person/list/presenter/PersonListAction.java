package com.rygalang.androidexam.person.list.presenter;

import com.rygalang.androidexam.base.BaseAction;
import com.rygalang.androidexam.person.list.view.PersonListView;

/**
 * Created by Computer3 on 12/28/2017.
 */

public interface PersonListAction extends BaseAction<PersonListView> {
    void fetchPersonFromRemote();
    void fetchPersonFromDb();
}
