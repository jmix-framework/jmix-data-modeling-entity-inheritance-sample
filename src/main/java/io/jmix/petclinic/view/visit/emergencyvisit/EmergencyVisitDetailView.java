package io.jmix.petclinic.view.visit.emergencyvisit;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.view.*;
import io.jmix.petclinic.EmployeeRepository;
import io.jmix.petclinic.entity.User;
import io.jmix.petclinic.entity.visit.EmergencyVisit;
import io.jmix.petclinic.view.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "emergency-visits/:id", layout = MainView.class)
@ViewController(id = "petclinic_EmergencyVisit.detail")
@ViewDescriptor(path = "emergency-visit-detail-view.xml")
@EditedEntityContainer("emergencyVisitDc")
public class EmergencyVisitDetailView extends StandardDetailView<EmergencyVisit> {

    @Autowired
    private EmployeeRepository employeeRepository;
    @ViewComponent
    private EntityComboBox<User> assignedNurseField;

    @Subscribe
    public void onInit(final InitEvent event) {
        assignedNurseField.setItems(employeeRepository.findAllNurses());
    }
}