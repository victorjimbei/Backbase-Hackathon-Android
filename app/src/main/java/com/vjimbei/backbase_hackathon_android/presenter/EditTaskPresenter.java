package com.vjimbei.backbase_hackathon_android.presenter;

import com.vjimbei.backbase_hackathon_android.Mvp.EditTaskMvp;
import com.vjimbei.backbase_hackathon_android.entity.Task;
import com.vjimbei.backbase_hackathon_android.model.EditTaskModel;

public class EditTaskPresenter implements EditTaskMvp.Presenter, EditTaskModel.OnTaskUpdated {

    private EditTaskMvp.Model model;
    private EditTaskMvp.View view;

    public EditTaskPresenter(EditTaskMvp.View view) {
        this.view = view;
        model = new EditTaskModel(this);
    }

    @Override
    public void saveTask(Task task) {
        view.showProgress();
        model.updateTask(task);
    }

    @Override
    public void onSuccess(Task task) {
        view.reloadTask(task);
        view.hideProgress();
    }

    @Override
    public void onFailed() {
        view.hideProgress();
    }
}
