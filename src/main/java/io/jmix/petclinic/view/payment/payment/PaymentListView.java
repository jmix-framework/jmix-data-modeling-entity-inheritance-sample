package io.jmix.petclinic.view.payment.payment;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;
import io.jmix.petclinic.entity.payment.Payment;
import io.jmix.petclinic.view.main.MainView;


@Route(value = "payments", layout = MainView.class)
@ViewController(id = "petclinic_Payment.list")
@ViewDescriptor(path = "payment-list-view.xml")
@LookupComponent("paymentsDataGrid")
@DialogMode(width = "64em")
public class PaymentListView extends StandardListView<Payment> {
}