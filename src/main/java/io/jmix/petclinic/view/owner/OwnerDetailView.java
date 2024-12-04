package io.jmix.petclinic.view.owner;

import com.vaadin.flow.component.html.H3;
import io.jmix.flowui.DialogWindows;
import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.flowui.model.CollectionPropertyContainer;
import io.jmix.flowui.model.DataContext;
import io.jmix.petclinic.entity.owner.Owner;

import io.jmix.petclinic.entity.pet.Bird;
import io.jmix.petclinic.entity.pet.Cat;
import io.jmix.petclinic.entity.pet.Pet;
import io.jmix.petclinic.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import io.jmix.petclinic.view.pet.bird.BirdDetailView;
import io.jmix.petclinic.view.pet.cat.CatDetailView;
import io.jmix.petclinic.view.pet.pet.PetDetailView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "owners/:id", layout = MainView.class)
@ViewController("petclinic_Owner.detail")
@ViewDescriptor("owner-detail-view.xml")
@EditedEntityContainer("ownerDc")
public class OwnerDetailView extends StandardDetailView<Owner> {
    @ViewComponent
    private H3 nameHeader;
    @ViewComponent
    private MessageBundle messageBundle;
    @Autowired
    private ViewNavigators viewNavigators;
    @ViewComponent
    private DataGrid<Pet> petsDataGrid;
    @Autowired
    private DialogWindows dialogWindows;
    @ViewComponent
    private DataContext dataContext;
    @ViewComponent
    private CollectionPropertyContainer<Pet> petsDc;

    @Subscribe
    public void onReady(final ReadyEvent event) {
        nameHeader.setText(messageBundle.formatMessage("ownerNameHeader", getEditedEntity().getFullName()));
    }


    @Subscribe("petsDataGrid.createPet")
    public void onPetsDataGridCreatePet(final ActionPerformedEvent event) {
        openPetDetailView(Pet.class, PetDetailView.class);
    }

    @Subscribe("petsDataGrid.createCat")
    public void onPetsDataGridCreateCat(final ActionPerformedEvent event) {
        openPetDetailView(Cat.class, CatDetailView.class);
    }

    @Subscribe("petsDataGrid.createBird")
    public void onPetsDataGridCreateBird(final ActionPerformedEvent event) {
        openPetDetailView(Bird.class, BirdDetailView.class);
    }

    private <T extends Pet> void openPetDetailView(Class<T> entityClass, Class<? extends StandardDetailView<T>> detailViewClass) {
        T pet = dataContext.create(entityClass);
        pet.setOwner(getEditedEntity());

        dialogWindows.detail(this, entityClass)
                .newEntity(pet)
                .withViewClass(detailViewClass)
                .withParentDataContext(dataContext)
                .withAfterCloseListener(e -> {
                    if (e.closedWith(StandardOutcome.SAVE)) {
                        petsDc.getMutableItems().add(pet);
                    }
                })
                .open();
    }
}
