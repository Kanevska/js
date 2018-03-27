import {Services} from './services/Services';
require('./css/style.css');
location.hash = '/departmentList';
new Services().controller();