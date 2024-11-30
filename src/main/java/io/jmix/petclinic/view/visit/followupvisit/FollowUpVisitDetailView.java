package io.jmix.petclinic.view.visit.followupvisit;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.view.*;
import io.jmix.petclinic.EmployeeRepository;
import io.jmix.petclinic.entity.User;
import io.jmix.petclinic.entity.visit.FollowUpVisit;
import io.jmix.petclinic.view.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "follow-up-visits/:id", layout = MainView.class)
@ViewController(id = "petclinic_FollowUpVisit.detail")
@ViewDescriptor(path = "follow-up-visit-detail-view.xml")
@EditedEntityContainer("followUpVisitDc")
public class FollowUpVisitDetailView extends StandardDetailView<FollowUpVisit> {
    @Autowired
    private EmployeeRepository employeeRepository;
    @ViewComponent
    private EntityComboBox<User> assignedNurseField;

    @Subscribe
    public void onInit(final InitEvent event) {
        assignedNurseField.setItems(employeeRepository.findAllNurses());
    }
}