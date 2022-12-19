const fetch = require("node-fetch-commonjs")
const ReportModel = require("./models/Report.model")
const { connect } = require("mongoose")

async function requestAllApis() {
    const authServiceStatus = await fetch("http://localhost:8080/api/auth/health")
    const productsServiceStatus = await fetch("http://localhost:8080/api/products/health")
    const searchServiceStatus = await fetch("http://localhost:8080/api/search/health")
    await ReportModel.create({service: 'Auth', status: authServiceStatus.status, timestamp: Date.now()})  
    await ReportModel.create({service: 'Products', status: productsServiceStatus.status, timestamp: Date.now()})
    await ReportModel.create({service: 'Search', status: searchServiceStatus.status, timestamp: Date.now()})

    console.log(`Generated new reports timestamp: ${Date.now()}`);
    console.log(`Next report on timestamp ${Date.now() + 3600000}`);
}

connect('mongodb+srv://ecommerce:0WL2JntbH3PgPe9N@cluster0.9k5byqk.mongodb.net/?retryWrites=true&w=majority', {
    dbName: 'edb'
})
.then(() => {
    console.log('Db connected');
})


requestAllApis()

setInterval(() => {
    requestAllApis()
}, 3600000)