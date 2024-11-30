package io.jmix.petclinic.view.pet.cat;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.petclinic.entity.pet.Cat;
import io.jmix.petclinic.view.main.MainView;

@Route(value = "cats/:id", layout = MainView.class)
@ViewController(id = "petclinic_Cat.detail")
@ViewDescriptor(path = "cat-detail-view.xml")
@EditedEntityContainer("catDc")
public class CatDetailView extends StandardDetailView<Cat> {
}