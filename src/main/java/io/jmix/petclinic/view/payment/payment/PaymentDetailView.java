package io.jmix.petclinic.view.payment.payment;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import io.jmix.petclinic.entity.payment.Payment;
import io.jmix.petclinic.view.main.MainView;

@Route(value = "payments/:id", layout = MainView.class)
@ViewController(id = "petclinic_Payment.detail")
@ViewDescriptor(path = "payment-detail-view.xml")
@EditedEntityContainer("paymentDc")
public class PaymentDetailView extends StandardDetailView<Payment> {
}