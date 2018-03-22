import {Router} from './router/Router';
import {Services} from './services/Services';
location.hash = '/departmentList';
const router = new Router();
const service = new Services();
service.getInformation('/departments/departmentList',router);
