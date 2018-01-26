/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {
    Component
} from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    ListView,
    WebView,
    Player,
    Navigator,
    Linking
} from 'react-native';
import Hyperlink from 'react-native-hyperlink'
import Detail from './Detail.js'

export default class VideoReview extends Component {
    static navigationOptions = {
        title: 'Home',
    };
    constructor() {
        super();
        const ds = new ListView.DataSource({
            rowHasChanged: (r1, r2) => r1 !== r2
        });
        this.state = {
            contents: ds.cloneWithRows(['row 1', 'row 2'])
        };
    }

    componentWillMount() {

        // fetch("http://xdn.wokao.co/").done((html)=>{   alert(html); });
        fetch("http://xdn.wokao.co")
            .then((response) => response.text())
            .then((response) => {
                console.log("responsed.")
                response = response
                    .replace(/\&raquo\;/, "&nbsp;")
                    .replace(/\&laquo\;/, "&nbsp;");

                var DomParser = require('react-native-html-parser')
                    .DOMParser
                let doc = new DomParser()
                    .parseFromString(response, 'text/html')
                // console.log(doc.querySelect("a"))
                var obj = {};

                this.setState({
                    contents: this
                        .state
                        .contents
                        .cloneWithRows(doc.querySelect(
                            "#videos > div:nth-child(2) > a")
                                       .map((a) => {
                                           return {
                                               url: (a.attributes[0].nodeValue||"").trim(),
                                               title: (a.firstChild.data||"").trim()
                                           };
                                       })
                                       .filter((o) => o.title.length !== 0 && o.url.length !==
                                               0)
                                       .filter((o) => {
                                           var b = obj[o.title];
                                           obj[o.title] = o.url;
                                           return !b;
                                       })
                                       .map((o)=>{
                                           var id = (o.url||"").match(/\d+/)[0];
                                           o.url = `http://xdn.wokao.co/videos/0/${id}/${id}.240p.ts`;
                                           return o;
                                       })
                                      )
                });
            })
            .catch((error) => {
                alert(error);
            });
    }

    renderPlayer(obj){
        return
        (<Player
         source={{
             uri:obj.url,
             controller:true,
             timeout:10*10000,
             live:true,
             hardCodes:false
         }}
         started={true}
         muted={false}
         style={{
             height:200,
             width:200,
         }}
         onLoading={()=>{}}
         onPaused={()=>{}}
         onShutdown={()=>{}}
         onError={()=>{}}
         onPlaying={()=>{}}
         />

        )
    }
    
    render() {
        
        const routes = [
            {title: 'VideoReview', index: 0},
            {title: 'Detail', index: 1},
        ];
        return (
                <View style={styles.container}>
                <Text style={styles.welcome}>Resources 1</Text>
                <ListView
            dataSource={this.state.contents}
            renderRow={(obj) => <View>
                       <Text onClick={()=> Linking.openURL(obj.url).catch(err => console.error('An error occurred', err)) }>{obj.title}</Text>
                       <Text>{obj.url}</Text>
                       {this.renderPlayer(obj)}
                       </View>
                      }
                />
                </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF'
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5
    }
});

AppRegistry.registerComponent('VideoReview', () => VideoReview);
