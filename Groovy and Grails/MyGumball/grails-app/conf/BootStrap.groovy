import GumballMachineRest.Gumball

class BootStrap {

    def init = { servletContext ->
		def gum = new Gumball(modelNumber: 'M102988',serialNumber: '1234998871109',countGumballs: 5)
		gum.save()
		}
    def destroy = {
    }
}
