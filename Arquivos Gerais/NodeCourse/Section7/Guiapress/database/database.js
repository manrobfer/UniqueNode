const sequelize = require("sequelize")

const connection = new sequelize('NodeData', 'nodeuser','nodejs12345',{
    host: 'localhost',
    dialect: 'mysql',
    timezone: "-3:00"
});

module.exports = connection;