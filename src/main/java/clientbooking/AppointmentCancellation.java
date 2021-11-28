package clientbooking;

import constants.ManagementSystemException;
import entities.*;

/**
 * This is the Use Case for cancelling appointments.
 * Every time the use case is needed, a new AppointmentCancellation instance is created
 * with the only parameters being the clinic and appointment ID
 */

public class AppointmentCancellation {

    final int appointmentId;
    final ClinicDecorator clinic;

    /**
     * creates Use Case for canceling appointments.
     *
     * @param appointmentId the id of the appointment
     * @param clinic the clinic where the appointment suppose to happen
     */
    public AppointmentCancellation(int appointmentId, ClinicDecorator clinic){
        this.appointmentId = appointmentId;
        this.clinic = clinic;
    }

    /**
     * Delete the appointment.
     *
     * @return true if process successfully; false otherwise.
     * @throws ManagementSystemException if there is no appointment to delete.
     */
    public String deleteAppointment() throws ManagementSystemException {
        Appointment appointment = clinic.getAppointmentRecord(this.appointmentId);
        if (appointment.getClient().getHasAppointment()) {
            appointment.getClient().disapproveAppointment();
            clinic.removeAppointmentById(this.appointmentId);
            appointment.getTimePeriod().addAvailableSlot();
            return appointment.toString();
        }
        throw new ManagementSystemException(ManagementSystemException.APPOINTMENT_DOES_NOT_EXIST);
    }


}