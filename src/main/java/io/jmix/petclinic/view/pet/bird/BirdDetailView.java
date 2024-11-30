package io.jmix.petclinic.view.pet.bird;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.petclinic.entity.pet.Bird;
import io.jmix.petclinic.view.main.MainView;

@Route(value = "birds/:id", layout = MainView.class)
@ViewController(id = "petclinic_Bird.detail")
@ViewDescriptor(path = "bird-detail-view.xml")
@EditedEntityContainer("birdDc")
public class BirdDetailView extends StandardDetailView<Bird> {
}