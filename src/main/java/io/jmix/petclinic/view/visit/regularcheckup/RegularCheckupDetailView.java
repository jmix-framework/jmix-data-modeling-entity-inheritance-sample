package io.jmix.petclinic.view.visit.regularcheckup;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.component.combobox.EntityComboBox;
import io.jmix.flowui.view.*;
import io.jmix.petclinic.EmployeeRepository;
import io.jmix.petclinic.entity.User;
import io.jmix.petclinic.entity.visit.RegularCheckup;
import io.jmix.petclinic.view.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "regular-checkups/:id", layout = MainView.class)
@ViewController(id = "petclinic_RegularCheckup.detail")
@ViewDescriptor(path = "regular-checkup-detail-view.xml")
@EditedEntityContainer("regularCheckupDc")
public class RegularCheckupDetailView extends StandardDetailView<RegularCheckup> {

    @Autowired
    private EmployeeRepository employeeRepository;
    @ViewComponent
    private EntityComboBox<User> assignedNurseField;

    @Subscribe
    public void onInit(final InitEvent event) {
        assignedNurseField.setItems(employeeRepository.findAllNurses());
    }
}