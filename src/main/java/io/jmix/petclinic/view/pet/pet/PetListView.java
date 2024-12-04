package io.jmix.petclinic.view.pet.pet;

import io.jmix.flowui.ViewNavigators;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.component.propertyfilter.PropertyFilter;
import io.jmix.flowui.kit.action.ActionPerformedEvent;
import io.jmix.petclinic.entity.pet.Pet;

import io.jmix.petclinic.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import io.jmix.petclinic.view.pet.bird.BirdDetailView;
import io.jmix.petclinic.view.pet.cat.CatDetailView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "pets", layout = MainView.class)
@ViewController("petclinic_Pet.list")
@ViewDescriptor("pet-list-view.xml")
@LookupComponent("petsDataGrid")
@DialogMode(width = "50em")
public class PetListView extends StandardListView<Pet> {

    @ViewComponent
    private PropertyFilter identificationNumberFilter;
    @ViewComponent
    private PropertyFilter typeFilter;
    @ViewComponent
    private PropertyFilter ownerFilter;
    @Autowired
    private ViewNavigators viewNavigators;
    @ViewComponent
    private DataGrid<Pet> petsDataGrid;

    @Subscribe("clearFilterAction")
    public void onClearFilterAction(final ActionPerformedEvent event) {
        identificationNumberFilter.clear();
        typeFilter.clear();
        ownerFilter.clear();
    }

    // tag::pet-create-actions[]
    @Subscribe("petsDataGrid.createPet")
    public void onPetsDataGridCreatePet(final ActionPerformedEvent event) {
        viewNavigators.detailView(petsDataGrid)
                .withViewClass(PetDetailView.class)
                .newEntity()
                .navigate();
    }

    @Subscribe("petsDataGrid.createCat")
    public void onPetsDataGridCreateCat(final ActionPerformedEvent event) {
        viewNavigators.detailView(petsDataGrid)
                .withViewClass(CatDetailView.class)
                .newEntity()
                .navigate();
    }

    @Subscribe("petsDataGrid.createBird")
    public void onPetsDataGridCreateBird(final ActionPerformedEvent event) {
        viewNavigators.detailView(petsDataGrid)
                .withViewClass(BirdDetailView.class)
                .newEntity()
                .navigate();
    }
    // end::pet-create-actions[]
}
