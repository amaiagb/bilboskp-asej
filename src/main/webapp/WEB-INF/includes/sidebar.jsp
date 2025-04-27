<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" >
            <!-- 
                
                <div class="sidebar-brand-text mx-3"></div>
                 -->
                 <div class="sidebar-brand-icon">
                    <img class="logo" alt="" src="/bilboskp-asej/assets/img/logos/bilboSKP_light_xs.png">
                    <img class="logo_xs" alt="" src="/bilboskp-asej/assets/img/logos/SKP_xs.png">
                </div>
                 
            </a>

            <!-- Divider -->
            <li class="nav-item">
            	<form action="" method="get" class="d-flex" style="width: auto;">
					<select name="lang" class="form-select text-black border-0" onchange="this.form.submit()">
						<option value="es" ${lang == 'es' ? 'selected' : ''}>Español</option>
    					<option value="en" ${lang == 'en' ? 'selected' : ''}>English</option>
					</select>
				</form>
            </li>
            
            
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="/bilboskp-asej/inicio">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span><fmt:message key="inicio"/></span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                <fmt:message key="juego"/>
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-play"></i>
                    <span><fmt:message key="juego"/>s</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">

                        <h6 class="collapse-header"><fmt:message key="juego"/>:</h6>
                        <a class="collapse-item" href="/bilboskp-asej/iniciarOrganizar"><fmt:message key="sidebar.organizar"/></a>
                        <a class="collapse-item" href="/bilboskp-asej/listaPartidas"><fmt:message key="sidebar.listaP"/></a>
                        <a class="collapse-item" href="/bilboskp-asej/admin/codigo.jsp"><fmt:message key="sidebar.jugar"/></a>

                    </div>
                </div>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-ticket-alt"></i>
                    <span><fmt:message key="cupones"/></span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header"><fmt:message key="cupones"/>:</h6>
                        <a class="collapse-item" href="/bilboskp-asej/admin/comprarCupones.jsp"><fmt:message key="sidebar.comprar"/></a>
                        <a class="collapse-item" href="/bilboskp-asej/listaCupones"><fmt:message key="sidebar.listaC"/></a>
                        
                    </div>
                </div>
            </li>
             <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="/bilboskp-asej/ranking">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Ranking</span>
                </a>
                
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
              <fmt:message key="micuenta"/>
            </div>

            <!-- Nav Item - Pages Collapse Menu -->

            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="/bilboskp-asej/admin/suscripcion.jsp">
                    <i class="fas fa-fw fa-table"></i>
                    <span><fmt:message key="suscripcion"/></span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

            <!-- Sidebar Message -->
            

        </ul>