/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View
} from 'react-native';

// refer the sqlite
var SQLite = require('react-native-sqlite-storage')

export default class SqlLiteHelper extends Component {
    
    errorcb(err) {
        console.log("SQL Error: " + err);
    }

    successcb() {
        console.log("SQL executed fine");
    }

    opencb() {
        console.log("Database OPENED");
    }

    
    render() {
        
        var etmdburl = 'smb://192.168.31.1.xip.io/XiaoMi-usb0/ThunderDB/etm.db';
        fetch(etmdburl)
            .done((result)=>{
                console.log(result);
            });
        
        var db = SQLite.openDatabase(etmdburl, "1.0", "etm", 200000, this.opencb, this.errorcb);
        db.transaction((tx) => {
            console.log(tx);
            console.log("transaction begin");
            tx.executeSql('SELECT * FROM task_info', [], (tx, results) => {

                console.log("Query completed");

                // Get rows with Web SQL Database spec compliance.

                var len = results.rows.length;
                console.log(len);
                for (let i = 0; i < len; i++) {
                    let row = results.rows.item(i);
                    console.log(`Employee name: ${row.name}, Dept Name: ${row.deptName}`);
                }

                // Alternatively, you can use the non-standard raw method.

                /*
                  let rows = results.rows.raw(); // shallow copy of rows Array

                  rows.map(row => console.log(`Employee name: ${row.name}, Dept Name: ${row.deptName}`));
                */
            });
        });

        
        return (
                <View style={styles.container}>
                <Text style={styles.welcome}>
                Welcome to React Native!
            Rainbow Lu
            !!!!!!!!
            </Text>
                <Text style={styles.instructions}>
                To get started, edit index.ios.js
            </Text>
                <Text style={styles.instructions}>
                Press Cmd+R to reload,{'\n'}
            Cmd+D or shake for dev menu
            </Text>
                </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});

AppRegistry.registerComponent('SqlLiteHelper', () => SqlLiteHelper);
