import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import DataTable from 'react-data-table-component';
import Container from "react-bootstrap/Container";

export default class App extends React.Component {
    // eslint-disable-next-line no-useless-constructor
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            items: []
        };
    }

    componentDidMount() {
        fetch('http://localhost:8080/devices')
            .then(res => res.json())
            .then((result) => {
                    this.setState({
                        isLoaded: true,
                        items: result
                    });
                },
                // Note: it's important to handle errors here
                // instead of a catch() block so that we don't swallow
                // exceptions from actual bugs in components.
                (error) => {
                    console.log(error);
                    this.setState({
                        isLoaded: true,
                        error
                    });
                }
            )
    }

    render() {
        const columns = [
            {
                name: 'Name',
                selector: 'name',
                sortable: true,
            },
            {
                name: 'AgentVersion',
                selector: 'agentVersion',
                sortable: true,
                right: true,
            },
            {
                name: 'CpuModel',
                selector: 'cpuModel',
                sortable: true,
                right: true,
            },
            {
                name: 'Description',
                selector: 'description',
                sortable: true,
                right: true,
            },
            {
                name: 'DiscoveryDate',
                selector: 'discoveryDate',
                sortable: true,
                right: true,
            },
            {
                name: 'HowManyAlerts',
                selector: 'howManyAlerts',
                sortable: true,
                right: true,
            },
            {
                name: 'IpAddresses',
                sortable: true,
                right: true,
                cell: row => <div>{row.ipAddresses}</div>,
            }
        ];
        if(this.state.items.length > 0){
            this.state.items.map(item => {
                item.discoveryDate = new Date(item.discoveryDate).toDateString();
                item.ipAddresses = item.ipAddresses.map(i => <div><div>{i}</div></div>)
            });
            console.log(this.state.items[0]);
        }

        return (
            <div className="App">
                <header className="App-header">
                    <img src={'https://ubywz0glkz-flywheel.netdna-ssl.com/wp-content/uploads/Auriga_Logo.jpg'}
                         className="App-logo" alt="logo"/>
                    <p>
                        <code>A React Application</code>
                    </p>
                    <h1>
                        Interview Excercise
                    </h1>
                    <p>
                        That consumes an http endpoint resources to display devices information that describes the
                        details
                        of the devices found
                    </p>
                </header>
                <Container>
                    <DataTable
                        title=""
                        columns={columns}
                        data={this.state.items}
                        selectableRows
                        expandableRows
                    />
                </Container>
            </div>
        );
    }
}
